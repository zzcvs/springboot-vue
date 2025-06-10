package com.zc.Mybatisplus.junit;


import com.baomidou.mybatisplus.annotation.TableName;
import com.zc.Mybatisplus.entity.mybatisplusdb;
import com.zc.Mybatisplus.mapper.mybatisplusdbMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


@SpringBootTest
@TableName("mybatisplus_db")
public class TestUsers {

    @Autowired
    private mybatisplusdbMapper mybatisplusdbMapper;

    @Test
    public void test1(){
        List<mybatisplusdb> list = mybatisplusdbMapper.selectList(null);
        for (mybatisplusdb mybatisplusdb : list){
            System.out.println(mybatisplusdb);
        }
    }

}
