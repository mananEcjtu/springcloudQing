package com.test.service.impl;

import com.test.entity.User;
import com.test.mapper.UserMapper;
import com.test.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserById(int uid) {
        return userMapper.getUserById(uid);
    }

    @Override
    public int getRemain(int uid) {
        return userMapper.getUserBookRemain(uid);
    }

    @Override
    public boolean setRemain(int uid, int count) {
        return userMapper.updateBookCount(uid, count) > 0;
    }

}
