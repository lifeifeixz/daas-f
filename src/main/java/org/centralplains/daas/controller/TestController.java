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
package org.centralplains.daas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author flysLi
 * @ClassName TestController
 * @Decription TODO
 * @Date 2019/1/11 11:45
 * @Version 1.0
 */
@RestController
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping(value = "/kafka/send")
    public Object sendKafka(HttpServletRequest request, HttpServletResponse response) {
        String message = request.getParameter("message");
        System.out.println("消息:" + message);
        kafkaTemplate.send("test", "key", message);
        return "发送成功";
    }
}
