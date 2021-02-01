package com.nit.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {

    @NacosValue(value = "${template}", autoRefreshed = true)
    private String template;

//    @Autowired
//    StringRedisTemplate stringRedisTemplate;

//    @Resource(name = "preRedis")
//    private RedisTemplate<String, Object> preRedis;


    @RequestMapping("/")
    @ResponseBody
    public String getHello(){
        log.info("request comming .....");
        return "hello";
    }

    @RequestMapping("/error")
    @ResponseBody
    public String error() throws Exception{
        throw new Exception("Something wrong .....");
    }

    @RequestMapping("/getNacosValue")
    @ResponseBody
    public String getNacosValue() throws Exception{
        return template;
    }

//    @RequestMapping("/getRedisValue")
//    @ResponseBody
//    public String getRedisValue() throws Exception{
//        return stringRedisTemplate.opsForValue().get("demo");
//    }
}
