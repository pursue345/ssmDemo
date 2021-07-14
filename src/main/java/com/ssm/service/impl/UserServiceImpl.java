package com.ssm.service.impl;

import com.ssm.mapper.UserMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserMapper userMapper;

    @Override
    public User queryById(int id) {
        return userMapper.queryById(id);
    }
}
