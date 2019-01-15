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
package org.centralplains.daas;

import com.alibaba.fastjson.JSONObject;
import org.centralplains.daas.util.HttpClientUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName FaceTest
 * @Decription TODO
 * @Date 2019/1/11 16:30
 * @Version 1.0
 */
public class FaceTest {
    public static void main(String[] args) throws IOException {
        String uri = "http://aliyun.88gongxiang.com:8000/api/v1/user/face/verify?token=872DEE475494B646F8D3326993D51E44";
        Map<String, Object> params = new HashMap<>();
        byte[] data = Util.getBytes("D:\\L临时数据\\图片(证件)\\人脸识别2.jpg");
        String base64Str = Util.getImageStr(data);
        String imgData = URLEncoder.encode(base64Str, "UTF-8");
        params.put("image", imgData);
        String reslut = HttpClientUtils.post(uri, params);
        System.out.println(reslut);
    }
}
