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
package org.centralplains.daas.beans.res;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName MapPriceResp
 * @Decription TODO
 * @Date 2018/12/14 15:20
 * @Version 1.0
 */
public class MapPriceResp implements Serializable {

    private static final long serialVersionUID = -8372185382303694941L;
    private List<Map<String, Object>> data;
    private Map<String, Double[]> geoCoordMap;

    public MapPriceResp(List<Map<String, Object>> data, Map<String, Double[]> geoCoordMap) {
        this.data = data;
        this.geoCoordMap = geoCoordMap;
    }

    public List<Map<String, Object>> getData() {
        return data;
    }

    public void setData(List<Map<String, Object>> data) {
        this.data = data;
    }

    public Map<String, Double[]> getGeoCoordMap() {
        return geoCoordMap;
    }

    public void setGeoCoordMap(Map<String, Double[]> geoCoordMap) {
        this.geoCoordMap = geoCoordMap;
    }
}
