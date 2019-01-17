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

import io.swagger.annotations.Api;
import org.centralplains.daas.beans.Product;
import org.centralplains.daas.common.Keys;
import org.centralplains.daas.service.CacheService;
import org.centralplains.daas.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author flysLi
 * @ClassName DataSyncController
 * @Decription TODO
 * @Date 2019/1/10 14:29
 * @Version 1.0
 */
@RestController
@Api(description = "数据同步")
@Description(value = "数据同步控制器")
@RequestMapping(value = "/data/sync")
public class DataSyncController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 同步数据至mysql数据库
     *
     * @param date
     * @param name
     */
    @RequestMapping(value = "toMysql")
    public void syncDataToMySql(@RequestParam(required = false) String date,
                                @RequestParam(required = false) String name) {
        Set<String> keys = redisTemplate.opsForHash().keys(Keys.Cache_Product);
        for (String key : keys) {
            String[] str1 = key.split("_");
            //处理日期
            List<Product> products = productService.findProducts(str1[0], str1[1]);


            if (products == null || products.size() == 0) {
                List<Product> products1 = (List<Product>) cacheService.get(Keys.Cache_Product, key);
                for (Product p : products1) {
                    //查看数据是否已经存在
                    productService.save(p);
                }
            }
        }
    }
}
