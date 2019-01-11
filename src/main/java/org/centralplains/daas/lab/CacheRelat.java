package org.centralplains.daas.lab;


import com.sun.corba.se.spi.ior.ObjectKey;
import org.centralplains.daas.lab.bean.User;
import org.centralplains.daas.service.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/**
 * 尝试设计一种关系型缓存数据结构。
 */
@RestController
@RequestMapping(value = "/cache/relat")
public class CacheRelat {
    @Autowired
    private CacheService cacheService;

    @Autowired
    private RedisTemplate redisTemplate;

    public static void main(String[] args) {


    }
    private Map<String,Object> data= new ConcurrentHashMap<>(1000);
    @RequestMapping(value = "/insert")
    public Object add(@RequestParam String h,
                      @RequestParam String k,
                      @RequestParam Object v) {
        data.put(k,v);
        return "执行结束，请查看客户端!";
    }

    @RequestMapping(value = "/get")
    public Object get(@RequestParam String k) {
        return data.get(k);
    }
}
