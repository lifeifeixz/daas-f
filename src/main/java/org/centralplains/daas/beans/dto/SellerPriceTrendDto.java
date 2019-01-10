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
package org.centralplains.daas.beans.dto;

/**
 * @author flysLi
 * @ClassName SellerPriceTrendDto
 * @Decription TODO
 * @Date 2019/1/10 16:10
 * @Version 1.0
 */
public class SellerPriceTrendDto {
    private String seller;
    private String name;
    private Integer count;

    public SellerPriceTrendDto(String seller, String name) {
        this.seller = seller;
        this.name = name;
    }

    public SellerPriceTrendDto(String seller, String name, Integer count) {
        this.seller = seller;
        this.name = name;
        this.count = count;
    }

    public SellerPriceTrendDto() {
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
