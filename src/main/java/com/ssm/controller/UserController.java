package com.ssm.controller;

import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserService userService;

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public User queryById(@PathVariable("id") int id){
        logger.info("进入userController...");
        return userService.queryById(id);
    }

}
