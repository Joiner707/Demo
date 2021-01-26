package com.nit.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/hello")
public class HelloController {


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
}
