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
package org.centralplains.daas.beans.req;

/**
 * @author flysLi
 * @ClassName SyncReq
 * @Decription TODO
 * @Date 2018/12/14 17:32
 * @Version 1.0
 */
public class SyncReq {
    private String dateForm;
    private String dateTo;
    private String varietyCode;

    public String getDateForm() {
        return dateForm;
    }

    public void setDateForm(String dateForm) {
        this.dateForm = dateForm;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public String getVarietyCode() {
        return varietyCode;
    }

    public void setVarietyCode(String varietyCode) {
        this.varietyCode = varietyCode;
    }

    public SyncReq(String dateForm, String dateTo, String varietyCode) {
        this.dateForm = dateForm;
        this.dateTo = dateTo;
        this.varietyCode = varietyCode;
    }

    public SyncReq() {
    }
}
