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
package sync;

import com.alibaba.fastjson.JSONObject;
import org.centralplains.daas.util.HttpClientUtils;
import org.centralplains.daas.util.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName ReqData
 * @Decription TODO
 * @Date 2019/1/10 13:44
 * @Version 1.0
 */
public class ReqData implements Runnable {
    private String name;
    public String url = "http://114.116.24.215:8080/prd/variety/regionPrice";

    public ReqData(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        long s = System.currentTimeMillis();
        Map<String, Object> param = new HashMap<>(6);
        param.put("varietyName", name);
        param.put("date", "2019-01-09");
//        String data = HttpUtil.post(url, param);
        String data = HttpClientUtils.post(url, JSONObject.toJSONString(param));
        System.out.println(data);
        long e = System.currentTimeMillis();
        System.out.println("[ " + name + " ]\t 数据采集完成!耗时:" + ((e - s) / 1000) + "秒");
    }

    public static void main(String[] args) {
        ReqData reqData = new ReqData("西瓜");
        reqData.run();
    }
}
