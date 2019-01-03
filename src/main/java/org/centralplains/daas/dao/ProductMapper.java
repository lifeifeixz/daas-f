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

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.centralplains.daas.beans.Product;

import java.util.List;

/**
 * @author flysLi
 * @ClassName ProductMapper
 * @Decription TODO
 * @Date 2018/12/14 13:57
 * @Version 1.0
 */
@Mapper
public interface ProductMapper {
    List<Product> findSellerAll(String date);

    List<Product> findSellerPrice(@Param("name")String name, @Param("date")String date);
}
