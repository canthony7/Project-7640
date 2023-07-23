package com.hkbu.project7640.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hkbu.project7640.entity.User;
import com.hkbu.project7640.mapper.UserMapper;
import com.hkbu.project7640.service.IUserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chet
 * @since 2023-04-03
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("telephone", username);
        wrapper.eq("password", password);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public User getUserByName(String username) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("telephone", username);
        return userMapper.selectOne(wrapper);
    }
}
