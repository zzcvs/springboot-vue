package com.gec.ocean;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication

public class OceanApplication {

    public static void main(String[] args){
        System.setProperty("druid.mysql.usePingMethod","false");//////加了个没什么用的东西，给花了20块，下次别要了，说是国内阿里的连接池bug多。
        SpringApplication.run(OceanApplication.class,args);
    }

}
