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

import com.alibaba.fastjson.JSONObject;
import org.centralplains.daas.beans.Location;
import org.centralplains.daas.beans.LocationInvalid;
import org.centralplains.daas.components.MapApi;
import org.centralplains.daas.dao.LocationInvalidRepository;
import org.centralplains.daas.dao.LocationRepository;
import org.centralplains.daas.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author flysLi
 * @ClassName TencentMapImpl
 * @Decription TODO
 * @Date 2018/12/14 13:43
 * @Version 1.0
 */
@Service
public class TencentMapImpl implements MapApi {

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocationInvalidRepository locationInvalidRepository;

    @Override
    public Location geoCoder(String address) {
        Location location = findByLoc(address);
        if (location != null) {
            return location;
        }
        String url = null;
        try {
            url = "https://apis.map.qq.com/ws/geocoder/v1/?address=" + URLEncoder.encode(address, "UTF-8") + "&key=XDEBZ-GU7KX-YFD4V-TGTPY-MIRLJ-TYBO5";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String data = HttpUtil.get(url, null, "UTF-8");
        JSONObject jsonObject = JSONObject.parseObject(data);
        int status = jsonObject.getInteger("status");
        if (status == 0) {
            JSONObject var2 = jsonObject.getJSONObject("result");
            JSONObject loc = var2.getJSONObject("location");
            location = new Location(loc.getDouble("lng"), loc.getDouble("lat"), address);
            location.setCity(var2.getJSONObject("address_components").getString("district"));
            return locationRepository.save(location);
        } else {
            System.out.println("[" + address + "]查询不到数据;具体原因可能: " + jsonObject.getString("message"));
            //将查询不到的数据存储到location_invalid表中
            LocationInvalid invalid = locationInvalidRepository.findByKeywords(address);
            if (invalid == null) {
                invalid = new LocationInvalid(0.0, 0.0, address);
                invalid.setCity(null);
                locationInvalidRepository.save(invalid);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        TencentMapImpl t = new TencentMapImpl();
        t.geoCoder("");
    }

    public Location findByLoc(String address) {
        return locationRepository.findByKeywords(address);
    }
}
