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

import org.centralplains.daas.beans.LocationInvalid;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author flysLi
 * @ClassName LocationInvalidRepository
 * @Decription TODO
 * @Date 2018/12/28 17:42
 * @Version 1.0
 */
public interface LocationInvalidRepository extends JpaRepository<LocationInvalid, Integer> {

    LocationInvalid findByKeywords(String name);
}
