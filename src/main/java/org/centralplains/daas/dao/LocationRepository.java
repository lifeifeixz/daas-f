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

import org.centralplains.daas.beans.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author flysLi
 * @ClassName LocationRepository
 * @Decription TODO
 * @Date 2018/12/14 17:07
 * @Version 1.0
 */
@Repository
public interface LocationRepository extends JpaRepository<Location,Integer>{

    Location findByKeywords(String keywords);
}
