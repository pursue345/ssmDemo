package com.ssm._1_jdbcutils;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 连接数据库
 */
public class JdbcUtils {
    public JdbcUtils() {
    }

    public static Connection openConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/ssm?useUnicode=true&characterEncoding=utf-8", "root", "123456");
        } catch (Exception var1) {
            var1.printStackTrace();
            return null;
        }
    }
}
