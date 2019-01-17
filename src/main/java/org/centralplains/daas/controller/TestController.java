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

import io.swagger.annotations.Api;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
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
@Api(description = "新功能集成测试接口")
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

    @RequestMapping(value = "/kafka/consume")
    public Object consumeKafka(HttpServletRequest request, HttpServletResponse response) {
        String message = request.getParameter("message");
        return "发送成功";
    }

//    @KafkaListener(topics = "test")
    public void receive(ConsumerRecord<?, ?> consumerRecord) {
        System.out.println("topic是:" + consumerRecord.topic() + "\tkey:" + consumerRecord.key() + "\tvalue:" + consumerRecord.value());
    }
}
