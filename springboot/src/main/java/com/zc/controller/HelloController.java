package com.zc.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class HelloController {

    @RequestMapping("/hello")
    public List<String> hello(){
        List<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");
        return list;
    }
}
