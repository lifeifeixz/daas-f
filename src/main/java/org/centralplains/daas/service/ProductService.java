package org.centralplains.daas.service;

import org.centralplains.daas.beans.Product;
import org.centralplains.daas.beans.req.ProductPageReq;
import org.centralplains.daas.beans.req.RegoinPriceReq;
import org.centralplains.daas.beans.req.SyncReq;
import org.centralplains.daas.beans.res.MapPriceResp;
import org.springframework.data.domain.Page;

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
    Object sync(SyncReq req);
}
