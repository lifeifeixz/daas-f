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
package org.centralplains.daas.components;

import org.centralplains.daas.beans.Location;

/**
 * @author flysLi
 * @ClassName MapApi
 * @Decription TODO
 * @Date 2018/12/14 13:42
 * @Version 1.0
 */
public interface MapApi {

    Location geoCoder(String address);
}
