package com.zc.Mybatisplus;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zc.Mybatisplus.mapper")
public class MybatisplusApp {

    public static void main(String[] args){
        SpringApplication.run(MybatisplusApp.class,args);
    }
}
