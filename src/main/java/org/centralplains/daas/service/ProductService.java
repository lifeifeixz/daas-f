package org.centralplains.daas.service;

import org.centralplains.daas.beans.Product;
import org.centralplains.daas.beans.req.ProductPageReq;
import org.centralplains.daas.beans.req.RegoinPriceReq;
import org.centralplains.daas.beans.req.SyncReq;
import org.centralplains.daas.beans.res.MapPriceResp;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author flysLi
 * @date 2018/12/13
 */
public interface ProductService {

    /**
     * 获取价格走势数据
     *
     * @param mark
     * @return
     */
    String reqPriceTrend(String mark);

    /**
     * 获取某个地区年份数据走势
     *
     * @param mark
     * @param craftIndex
     * @param year       年份；例：2018
     * @return
     */
    Double[] reqPriceTrend(String mark, String craftIndex, String year);

    /**
     * 获取所有产品信息
     *
     * @param req
     * @return
     */
    Page<Product> findAll(ProductPageReq req);

    /**
     * 地区价格数据接口
     *
     * @return
     */
    MapPriceResp regionPrice();

    /**
     * 检索各品种地区价格
     *
     * @param req
     * @return
     */
    MapPriceResp varietyRegoinPrice(RegoinPriceReq req);

    /**
     * 同步数据
     *
     * @param req
     * @return
     */
    List<Product> sync(SyncReq req);

    /**
     * 持久化
     *
     * @param product
     * @return
     */
    Product save(Product product);

    /**
     * 通过日期和名称获取产品
     *
     * @param name
     * @param date
     * @return
     */
    Product getProduct(String name, String date);

    List<Product> findProducts(String name, String date);

    /**
     * 获取地区价格趋势
     *
     * @param seller
     * @param name
     * @param count
     * @return
     */
    List<Product> getSellerPriceTrend(String seller, String name, Integer count);
}
