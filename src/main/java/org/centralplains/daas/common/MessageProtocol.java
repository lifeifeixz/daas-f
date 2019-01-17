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
 * @ClassName MessageProtocol
 * @Decription TODO
 * @Date 2019/1/17 15:08
 * @Version 1.0
 */
public class MessageProtocol<T> {
    private String type;

    private T protocol;

    public MessageProtocol() {
    }

    public MessageProtocol(String type, T protocol) {
        this.type = type;
        this.protocol = protocol;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public T getProtocol() {
        return protocol;
    }

    public void setProtocol(T protocol) {
        this.protocol = protocol;
    }
}
