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

import org.centralplains.daas.beans.Product;
import org.centralplains.daas.beans.req.ProductPageReq;
import org.centralplains.daas.beans.req.RegoinPriceReq;
import org.centralplains.daas.beans.req.SyncReq;
import org.centralplains.daas.beans.res.MapPriceResp;
import org.centralplains.daas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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

    @RequestMapping(value = "/cache/test")
    public Object cache() {
        redisTemplate.opsForHash().put("daas", "name", "李飞飞");

        return "操作结束";
    }
}
