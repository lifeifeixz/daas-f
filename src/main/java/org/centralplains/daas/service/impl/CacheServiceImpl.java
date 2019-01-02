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

import org.centralplains.daas.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author flysLi
 * @ClassName CacheServiceImpl
 * @Decription TODO
 * @Date 2019/1/2 14:59
 * @Version 1.0
 */
@Service
public class CacheServiceImpl implements CacheService {

    @Autowired
    RedisTemplate redisTemplate;

    @Override
    public void put(String h, String key, Object val) {
        redisTemplate.opsForHash().put(h, key, val);
    }

    @Override
    public Object get(String h, String key) {
        return redisTemplate.opsForHash().get(h,key);
    }
}
