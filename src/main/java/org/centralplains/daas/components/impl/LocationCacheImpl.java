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
package org.centralplains.daas.components.impl;

import org.centralplains.daas.beans.Location;
import org.centralplains.daas.components.LocationService;
import org.centralplains.daas.components.MapApi;
import org.centralplains.daas.dao.LocationRepository;
import org.centralplains.daas.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 操作缓存的实现
 *
 * @author flysLi
 * @ClassName LocationCacheImpl
 * @Decription TODO
 * @Date 2019/1/4 14:45
 * @Version 1.0
 */
@Component
public class LocationCacheImpl implements LocationService {
    public static final String LOCATION_KEY = "location";
    public static final String LOCATIONINVALID_KEY = "location_invalid";

    @Autowired
    private CacheService cacheService;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private MapApi mapApi;

    @Override
    public Location getByKeywords(String keywords) {
        //1.首先从缓存中取数据
        Location location = (Location) cacheService.get(LOCATION_KEY, keywords);
        if (location == null) {
            //2.远程API获取数据
            location = mapApi.geoCoder(keywords);
            //判断远程接口是否返回数据
            cacheService.put(location != null ? LOCATION_KEY : LOCATIONINVALID_KEY, keywords, location);
        }
        return location;
    }

    @Override
    public Location save(Location location) {
        cacheService.put(LOCATION_KEY, location.getKeywords(), location);
        return locationRepository.save(location);
    }
}
