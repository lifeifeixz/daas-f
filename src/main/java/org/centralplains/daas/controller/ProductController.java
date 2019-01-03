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
import org.centralplains.daas.beans.Variety;
import org.centralplains.daas.beans.req.ProductPageReq;
import org.centralplains.daas.beans.req.RegoinPriceReq;
import org.centralplains.daas.beans.req.SyncReq;
import org.centralplains.daas.beans.res.MapPriceResp;
import org.centralplains.daas.dao.VarietyRepository;
import org.centralplains.daas.service.CacheService;
import org.centralplains.daas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    private VarietyRepository varietyRepository;

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

    @RequestMapping(value = "/cache/test")
    public Object cache() {
        redisTemplate.opsForHash().put("daas", "name", "李飞飞");
        redisTemplate.opsForHash().put("variety", 1, varietyRepository.findByParentId(1));
        redisTemplate.opsForHash().put("variety", 2, varietyRepository.findByParentId(2));
        redisTemplate.opsForHash().put("variety", 3, varietyRepository.findByParentId(3));
        redisTemplate.opsForHash().put("variety", 4, varietyRepository.findByParentId(4));
        redisTemplate.opsForHash().put("variety", 5, varietyRepository.findByParentId(5));
        return "缓存完成";
    }

    @RequestMapping(value = "/variety/large")
    public List<Variety> large(@RequestParam int parentId) {
        return (List<Variety>) cacheService.get("variety", parentId);
    }
}
