package com.zc.mybatisplus.junit;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zc.mybatisplus.entity.Users;
import com.zc.mybatisplus.mapper.UserMapper;
import com.zc.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class TestUsers {
    @Autowired
    private UserMapper usersMapper;

    @Autowired
    private UserService userService;
//查
    @Test
    public void test() {
        List<Users> list = usersMapper.selectList(null);
        for (Users user : list) {
            System.out.println(user);
        }
    }
// 增
    @Test
    public void test01(){
        Users users = new Users();
        users.setName("楚神");
        users.setAge(25);
        users.setEmail("520@qq.com");
        int result = usersMapper.insert(users);
        System.out.println(result);
    }

// 改
    @Test //成功
    public void test02(){
        Users users = usersMapper.selectById(8L);
        users.setAge(19);
        int result = usersMapper.updateById(users);
        System.out.println(result);
    }

// 删
   @Test
   public void test03(){
        int result = usersMapper.deleteBatchIds(Arrays.asList(7));
        System.out.println(result);
   }


    @Test //成功
    public void test04(){
        Users users = usersMapper.selectById(8L);
        users.setEmail("5201314@qq.com");
        users.setVersion(users.getVersion() - 1);
        usersMapper.updateById(users);
    }

    @Test
    public void test05(){
        long pageNum = 2;
        long pageSize = 2;
        Page<Users> page = new Page<>(pageNum, pageSize);
        usersMapper.selectPage(page,null);
        for (Users user : page.getRecords()) {
            System.out.println(user);
        }
        System.out.println(page.getTotal());
        System.out.println(page.getPages());
    }
    @Test // 查
    public void test06(){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.eq("name","小朱");
        Users users = usersMapper.selectOne(wrapper);
        System.out.println(users);
    }

    @Test
    public void test07(){
        QueryWrapper<Users> wrapper = new QueryWrapper<>();
        wrapper.between("age",18,25);
        wrapper.eq("email","520@qq.com");
        List<Users> list = usersMapper.selectList(wrapper);
        for (Users user : list) {
            System.out.println(user);
        }
    }

    @Test
    public void test08(){
        List<Users> list = userService.list();
        for (Users users : list) {
            System.out.println(users);
        }
    }

}




