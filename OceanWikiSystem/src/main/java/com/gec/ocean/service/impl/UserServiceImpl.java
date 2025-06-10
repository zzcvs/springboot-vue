package com.gec.ocean.service.impl;

import com.gec.ocean.entity.User;
import com.gec.ocean.mapper.UserMapper;
import com.gec.ocean.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zhuzec
 * @since 2025-05-22
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
