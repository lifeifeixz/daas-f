/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package org.centralplains.daas.service.impl;

import org.assertj.core.util.DateUtil;
import org.centralplains.daas.beans.Product;
import org.centralplains.daas.beans.Variety;
import org.centralplains.daas.beans.req.ProductPageReq;
import org.centralplains.daas.beans.Location;
import org.centralplains.daas.beans.req.RegoinPriceReq;
import org.centralplains.daas.beans.req.SyncReq;
import org.centralplains.daas.beans.res.MapPriceResp;
import org.centralplains.daas.components.MapApi;
import org.centralplains.daas.dao.ProductMapper;
import org.centralplains.daas.dao.ProductRepository;
import org.centralplains.daas.dao.VarietyRepository;
import org.centralplains.daas.service.CacheService;
import org.centralplains.daas.service.ProductService;
import org.centralplains.daas.util.HttpUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author flysLi
 * @ClassName ProductServiceImpl
 * @Decription TODO
 * @Date 2018/12/13 11:57
 * @Version 1.0
 */
@SuppressWarnings("all")
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProductService productService;

    @Autowired
    private MapApi mapApi;

    @Autowired
    private VarietyRepository varietyRepository;

    @Autowired
    private CacheService cacheService;

    @Override
    public String reqPriceTrend(String mark) {
        String uri = "http://nc.mofcom.gov.cn/nc/channel/jghq2017/jg_list.do?action=priceAnalyse";
        Map<String, Object> params = new HashMap<>(5);
        params.put("type", "jgfx1");
        params.put("year1", "2017");
        params.put("year2", "2018");
        params.put("mark1", mark);
        params.put("craft_index", "13214");
        return HttpUtil.post(uri, params, "GBK");
    }

    @Override
    public Page<Product> findAll(ProductPageReq req) {
        return null;
    }

    @Override
    public MapPriceResp regionPrice() {
        Date date = new Date(System.currentTimeMillis() - (1000 * 60 * 60 * 24 * 2));
        //获取地区和价格
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Product> productList = productMapper.findSellerAll(sdf.format(date));
        //处理data
        List<Map<String, Object>> data = new ArrayList<>(50);
        Map<String, Double[]> geoCoordMap = new HashMap<>(50);
        for (Product product : productList) {
            Map<String, Object> item = new HashMap<>(1);
            item.put("name", product.getSeller());
            item.put("value", product.getPrice() * 10);
            Double[] position = new Double[2];
            Location loc = mapApi.geoCoder(product.getSeller());
            if (loc == null) {
                continue;
            }
            position[0] = loc.getLng();
            position[1] = loc.getLat();
            data.add(item);
            geoCoordMap.put(product.getSeller(), position);
        }
        return new MapPriceResp(data, geoCoordMap);
    }

    protected MapPriceResp regoinPrice(String varietyName, Date date) {
        boolean mapPriceExist = false;
        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
        String key = varietyName + dateStr;
        Object o = cacheService.get("mapPrice", key);
        if (o != null) {
            return (MapPriceResp) o;
        } else {
            MapPriceResp var1 = findMapProce(varietyName, dateStr);
            cacheService.put("mapPrice", key, var1);
            return var1;
        }
    }

    protected MapPriceResp findMapProce(String varietyName, String currentDate) {
        //获取地区和价格
        List<Product> productList = productMapper.findSellerPrice(varietyName, currentDate);
        if (productList != null && productList.size() > 0) {
            return convertMapPrice(productList);
        } else {
            Variety variety = varietyRepository.findByName(varietyName);
            syncRemoteData(currentDate, currentDate, variety.getCode());
            productList = productMapper.findSellerPrice(varietyName, currentDate);
            return convertMapPrice(productList);
        }
    }

    protected MapPriceResp convertMapPrice(List<Product> products) {
        if (products != null && products.size() > 0) {
            //处理data
            List<Map<String, Object>> data = new ArrayList<>(50);
            Map<String, Double[]> geoCoordMap = new HashMap<>(50);
            for (Product product : products) {
                Map<String, Object> item = new HashMap<>(1);
                item.put("name", product.getSeller());
                item.put("value", product.getPrice() * 10);
                Double[] position = new Double[2];
                Location loc = mapApi.geoCoder(product.getSeller());
                if (loc == null) {
                    continue;
                }
                position[0] = loc.getLng();
                position[1] = loc.getLat();
                data.add(item);
                geoCoordMap.put(product.getSeller(), position);
            }
            return new MapPriceResp(data, geoCoordMap);
        }
        return null;
    }

    @Override
    public MapPriceResp varietyRegoinPrice(RegoinPriceReq req) {
        return regoinPrice(req.getVarietyName(), req.getDate());
    }

    protected void syncRemoteData(String dateForm, String dateTo, String varietyCode) {
        sync(new SyncReq(dateForm, dateTo, varietyCode));
    }

    @Override
    public Object sync(SyncReq req) {
        int var1 = 20;
        String varietyCode = req.getVarietyCode();
        if (varietyCode == null) {
            varietyCode = "13214";//默认查询山药价格
        }
        String abstractUrl = "http://nc.mofcom.gov.cn/channel/jghq2017/price_list.shtml?" +
                "par_craft_index=13075&craft_index=" + varietyCode + "&par_p_index=&p_index=" +
                "&startTime=" + req.getDateForm() + "&endTime=" + req.getDateTo() + "&page=";
        for (int j = 1; j <= var1; j++) {
            String url = abstractUrl + j;
            Connection connection = Jsoup.connect(url);
            Document document = null;
            try {
                document = connection.get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //取出总页数
            if (j == 1) {
                String domStr = document.toString();
                String str = domStr.substring(domStr.indexOf("v_PageCount ="), domStr.indexOf("var pg = new showPages"));
                str = str.substring(str.indexOf("=") + 2, str.indexOf(";"));
                var1 = Integer.valueOf(str);
            }
            Element table = document.getElementsByClass("table-01").first();
            Elements trs = table.children().first().children();
            for (int i = 1; i < trs.size(); i++) {
                Element tr = trs.get(i);
                Elements tds = tr.children();
                String date = tds.get(0).text();
                String name = tds.get(1).text();
                Double price = Double.valueOf(tds.get(2).getElementsByClass("c-orange").eq(0).text());
                String seller = tds.get(3).text();
                String uri = tds.get(4).getElementsByTag("a").eq(0).attr("href");
                //解析mark1
                String priceTrend = uri.substring(uri.indexOf("mark1=") + 6, uri.indexOf("&"));
                Product product = new Product();
                product.setDate(DateUtil.parse(date));
                product.setName(name);
                product.setPrice(price);
                product.setPriceTrend(productService.reqPriceTrend(priceTrend));
                product.setSeller(seller);
                product.setCreateDate(new Date());
                productRepository.save(product);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();
        productService.sync(null);
    }
}
