package com.test.controller;

import com.test.config.TestProperties;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
//@RefreshScope
public class TestController {

//    @Value("${test.txt}")
//    public String txt;
//
//    @RequestMapping("/test")
//    public String test() {
//        System.out.println(txt);
//        return txt;
//    }

    @Resource
    private TestProperties properties;

//    @RequestMapping("/test")
//    public String test() {
//        System.out.println(properties.getTxt());
//        return properties.getTxt();
//    }

}
