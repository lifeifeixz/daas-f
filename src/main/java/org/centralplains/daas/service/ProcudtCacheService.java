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

import org.centralplains.daas.beans.Product;

import java.util.List;

/**
 * @author flysLi
 * @ClassName ProcudtCacheService
 * @Decription TODO
 * @Date 2019/1/4 15:43
 * @Version 1.0
 */
public interface ProcudtCacheService {

    void save(Product product);

    void saveAll(List<Product> productList);
}
