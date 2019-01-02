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
package org.centralplains.daas.dao;

import org.centralplains.daas.beans.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flysLi
 * @ClassName ProductMapper
 * @Decription TODO
 * @Date 2018/12/13 10:54
 * @Version 1.0
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}
