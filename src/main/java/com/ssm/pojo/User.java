package com.ssm.pojo;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Table(name = "user")
@Component
public class User {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    /**
     * 用户账号
     */
    @Column(name = "userCode")
    private String usercode;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户名
     */
    @Column(name = "userName")
    private String username;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 行为喜好
     */
    private String behavior;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户账号
     *
     * @return userCode - 用户账号
     */
    public String getUsercode() {
        return usercode;
    }

    /**
     * 设置用户账号
     *
     * @param usercode 用户账号
     */
    public void setUsercode(String usercode) {
        this.usercode = usercode;
    }

    /**
     * 获取用户密码
     *
     * @return password - 用户密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置用户密码
     *
     * @param password 用户密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取用户名
     *
     * @return userName - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取性别
     *
     * @return sex - 性别
     */
    public String getSex() {
        return sex;
    }

    /**
     * 设置性别
     *
     * @param sex 性别
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * 获取年龄
     *
     * @return age - 年龄
     */
    public Integer getAge() {
        return age;
    }

    /**
     * 设置年龄
     *
     * @param age 年龄
     */
    public void setAge(Integer age) {
        this.age = age;
    }

    /**
     * 获取行为喜好
     *
     * @return behavior - 行为喜好
     */
    public String getBehavior() {
        return behavior;
    }

    /**
     * 设置行为喜好
     *
     * @param behavior 行为喜好
     */
    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}