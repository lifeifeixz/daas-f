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
import java.util.HashMap;
import java.util.Map;

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
        String count = request.getParameter("count");
        if (count != null && count.length() > 0) {
            int len = Integer.parseInt(count);
            for (int i = 0; i < len; i++) {
                Map<String, Object> objectMap = new HashMap<>();
                objectMap.put("name", message + i);
                kafkaTemplate.send("test", "key", objectMap);
            }
        } else {
            Map<String, Object> objectMap = new HashMap<>();
            objectMap.put("name", message);
            kafkaTemplate.send("test", "key", objectMap);
        }

        return "发送成功";
    }
}
