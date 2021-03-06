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
package org.centralplains.daas.controller;

import org.apache.http.client.utils.DateUtils;
import org.centralplains.daas.beans.Location;
import org.centralplains.daas.beans.Product;
import org.centralplains.daas.beans.Variety;
import org.centralplains.daas.beans.req.ProductPageReq;
import org.centralplains.daas.beans.req.RegoinPriceReq;
import org.centralplains.daas.beans.req.SyncReq;
import org.centralplains.daas.beans.res.MapPriceResp;
import org.centralplains.daas.components.LocationService;
import org.centralplains.daas.components.impl.LocationCacheImpl;
import org.centralplains.daas.dao.LocationRepository;
import org.centralplains.daas.dao.VarietyRepository;
import org.centralplains.daas.service.CacheService;
import org.centralplains.daas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author flysLi
 * @ClassName ProductController
 * @Decription TODO
 * @Date 2018/12/13 14:41
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/prd")
public class ProductController {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    private ProductService productService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private CacheService cacheService;

    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Page<Product> pages(@RequestBody ProductPageReq req) {
        return productService.findAll(req);
    }

    @RequestMapping(value = "/more/{page}/{size}")
    public void test(int page, int size) {
        System.out.println("page:" + page);
        System.out.println("size:" + size);
    }

    @RequestMapping(value = "/regionPrice")
    public MapPriceResp regionPrice() {
        return productService.regionPrice();
    }

    @RequestMapping(value = "/variety/regionPrice")
    public MapPriceResp varietyRegionPrice(@RequestBody(required = false) @Valid RegoinPriceReq req) {
        return productService.varietyRegoinPrice(req);
    }

    @RequestMapping(value = "/sync")
    public Object sync(@RequestBody(required = false) @Valid SyncReq req) {
        return productService.sync(req);
    }

    @RequestMapping(value = "/getTrendByMark")
    public Object getTrendByCode(@RequestParam String make) {

        return productService.reqPriceTrend(make);
    }

    @RequestMapping(value = "/cache/test")
    public Object cache() {
        /*Add location data from Mysql to the cache*/
        List<Location> locations = locationRepository.findAll();
        int i = 0;
        for (Location location : locations) {
            if (location.getLat() != null && location.getLng() != null) {
                cacheService.put(LocationCacheImpl.LOCATION_KEY, location.getKeywords(), location);
                System.out.println(i++);
            }
        }
        return "缓存完成";
    }

    @RequestMapping(value = "/variety/large")
    public List<Variety> large(@RequestParam int parentId) {
        return (List<Variety>) cacheService.get("variety", parentId);
    }

    /**
     * 获取某个区域价格走势
     *
     * @param seller
     * @param name
     * @param count
     * @return
     */
    @RequestMapping(value = "/seller/priceTrend")
    public List<Product> priceTrend(@RequestParam() String seller,
                                    @RequestParam() String name,
                                    @RequestParam(required = false) Integer count) {
        return conventProduct(productService.getSellerPriceTrend(seller, name, count));
    }

    protected List<Product> conventProduct(List<Product> products) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        for (Product product : products) {
            String date = DateUtils.formatDate(product.getDate(), "yyyy-MM-dd");
            try {
                product.setDate(sdf.parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return products;
    }
}
