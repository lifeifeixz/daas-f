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
package org.centralplains.daas.common;

/**
 * @author flysLi
 * @ClassName ToMySqlProtocol
 * @Decription TODO
 * @Date 2019/1/17 15:09
 * @Version 1.0
 */
public class ToMySqlProtocol<T> {
    private String table;
    private String dropType;
    private String key;
    private T content;

    public ToMySqlProtocol() {
    }

    public ToMySqlProtocol(String table, String dropType, T content) {
        this.table = table;
        this.dropType = dropType;
        this.content = content;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getDropType() {
        return dropType;
    }

    public void setDropType(String dropType) {
        this.dropType = dropType;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
