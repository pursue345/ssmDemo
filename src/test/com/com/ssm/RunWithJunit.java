package com.ssm;

import com.ssm.controller.UserController;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Spring整合JUint测试
 * 1.pom.xml文件 引入 spring-test  与juint包
 */
//创建容器
@RunWith(SpringJUnit4ClassRunner.class)
//制定创建容器时使用哪个配置文件
@ContextConfiguration(locations = {"classpath:applicationContext.xml","classpath:springmvc.xml"})
@WebAppConfiguration
public class RunWithJunit {
    @Autowired
    User user;

    @Autowired
    private UserController userController;
//    @Autowired
//    DongglController dongglController;

    @Autowired
    UserService userService;

    @Test
    public void testCreateUser(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        Donggl quartzBean = context.getBean(Donggl.class);
        System.out.println("001-"+user);
//        System.out.println(quartzBean);
    }

    @Test
    public void testUser(){
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        User quartzBean = context.getBean(User.class);
        System.out.println("002-"+userController.queryById(1).getBehavior());
//        System.out.println(quartzBean);
    }

}
