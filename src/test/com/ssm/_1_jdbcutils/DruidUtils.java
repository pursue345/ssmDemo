package com.ssm._1_jdbcutils;

import com.alibaba.druid.pool.DruidDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DruidUtils {


    // 声明连接池对象
    private static DruidDataSource ds;
    static {
        // /实例化数据库连接池对象
        ds = new DruidDataSource();
        // 实例化配置对象
        Properties properties = new Properties();
        try {
            String resource = "C:\\idea_workspace\\ssmDemo\\src\\main\\resources\\db.properties";
            // 加载配置文件内容
            InputStream fis = new FileInputStream(resource);
            properties.load(fis);
            // 设置驱动类全称
            ds.setDriverClassName(properties.getProperty("jdbc.driverClassName"));
            // 设置连接的数据库
            ds.setUrl(properties.getProperty("jdbc.url1"));
            // 设置用户名
            ds.setUsername(properties.getProperty("jdbc.usernamername"));
            // 设置密码
            ds.setPassword(properties.getProperty("jdbc.password"));
            // 设置最大连接数量
            ds.setMaxActive(Integer.parseInt(properties.getProperty("maxActive")));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // 获取连接对象
    public static Connection getConnection() {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
