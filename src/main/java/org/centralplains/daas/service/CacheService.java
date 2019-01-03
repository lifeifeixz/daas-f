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
package org.centralplains.daas.service;

/**
 * @author flysLi
 * @ClassName CacheService
 * @Decription TODO
 * @Date 2019/1/2 14:56
 * @Version 1.0
 */
public interface CacheService {

    /**
     * 键值对存储
     *
     * @param key
     * @param val
     */
    void put(String h, String key, Object val);

    Object get(String h, Object key);
}
