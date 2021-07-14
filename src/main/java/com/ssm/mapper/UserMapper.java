package com.ssm.mapper;

import com.ssm.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserMapper extends Mapper<User> {

    public User queryById(int id);
}