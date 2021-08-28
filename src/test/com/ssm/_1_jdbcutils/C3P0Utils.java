package com.ssm._1_jdbcutils;


import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 从连接池子中获取连接！ C3P0的连接池子 0.获取连接池子对象 DBUtils 1.获取连接 2.关闭资源
 */
public class C3P0Utils {

    private static ComboPooledDataSource dataSource ;

    /**
     * 连接池中获取连接的方法
     * @return 连接
     */
    public static Connection getConnection() {

        Connection connection = null;
        // /实例化数据库连接池对象
        dataSource = new ComboPooledDataSource();
        // 实例化配置对象
        Properties properties = new Properties();
        try {
            String resource = "C:\\idea_workspace\\ssmDemo\\src\\main\\resources\\db.properties";
            // 加载配置文件内容
            InputStream fis = new FileInputStream(resource);
            properties.load(fis);
            // 设置驱动类全称
            dataSource.setDriverClass(properties.getProperty("jdbc.driverClassName"));
            // 设置连接的数据库
            dataSource.setJdbcUrl(properties.getProperty("jdbc.url1"));
            // 设置用户名
            dataSource.setUser(properties.getProperty("jdbc.username"));
            // 设置密码
            dataSource.setPassword(properties.getProperty("jdbc.password"));
            connection= dataSource.getConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    // 关闭资源
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static void close(Statement st) {

        if (st != null) {
            try {
                st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet set) {
        if (set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement st) {
        close(conn);
        close(st);
    }

    public static void close(Connection conn, Statement st, ResultSet rt) {
        close(conn);
        close(st);
        close(rt);
    }
}
