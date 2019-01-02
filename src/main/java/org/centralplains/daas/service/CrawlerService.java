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
package org.centralplains.daas.service;

import org.centralplains.daas.components.FollowUp;
import org.jsoup.nodes.Document;

/**
 * @author flysLi
 * @ClassName CrawlerService
 * @Decription TODO
 * @Date 2018/12/13 13:02
 * @Version 1.0
 */
public interface CrawlerService {

    /**
     * 彩技数据，并且支持回调
     *
     * @param uri
     * @param followUp 一个回调类
     */
    void crawler(Document document, FollowUp followUp);
}
