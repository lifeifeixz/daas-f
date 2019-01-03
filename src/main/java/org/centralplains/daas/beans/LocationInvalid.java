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
package org.centralplains.daas.beans;

import javax.persistence.*;

/**
 * @author flysLi
 * @ClassName LocationInvalid
 * @Decription TODO
 * @Date 2018/12/28 17:42
 * @Version 1.0
 */
@Entity
@Table(name = "location_invalid")
public class LocationInvalid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String city;
    private String keywords;
    private Double lng;
    private Double lat;

    public LocationInvalid() {
    }

    public LocationInvalid(Double lng, Double lat, String keywords) {
        this.lng = lng;
        this.lat = lat;
        this.keywords = keywords;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
}
