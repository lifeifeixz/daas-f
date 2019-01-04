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

import org.jsoup.nodes.Document;

/**
 * @author flysLi
 * @ClassName Analysis
 * @Decription TODO
 * @Date 2019/1/4 15:21
 * @Version 1.0
 */
public interface Analysis {

    <T> T resolve(Document document, T t);
}
