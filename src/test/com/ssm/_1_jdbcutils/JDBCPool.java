package com.ssm._1_jdbcutils;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *  dhcp连接池
 *  C3P0连接池
 *  druid连接池
 */
public class JDBCPool {

    //C3P0数据源
    private static ComboPooledDataSource dataSource = new ComboPooledDataSource();

    @Test
    public void test1() throws Exception {
        BasicDataSource source = new BasicDataSource();
        // 设置连接的信息
        source.setDriverClassName("com.mysql.jdbc.Driver");
        source.setUrl("jdbc:mysql://localhost:3306/ssm");
        source.setUsername("root");
        source.setPassword("123456");
        Connection connection = source.getConnection();
        String sql = "select * from account";
        Statement createStatement = connection.createStatement();
        ResultSet executeQuery = createStatement.executeQuery(sql);
        while (executeQuery.next()) {
            System.out.println(executeQuery.getString(1));
        }
        connection.close(); // 回收
    }


    @Test
    public void test2() {
        Connection conn = null;
        PreparedStatement pstat = null;
        ResultSet rs = null;
        try {
            conn = DruidUtils.getConnection();
            pstat = conn.prepareStatement("select * from account where name = ?");
            String name = "A";
            pstat.setString(1, name);
            rs = pstat.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println(id);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    @Test
    public void test3() throws Exception {
        // 1.创建C3P0连接池子
        Connection connection = C3P0Utils.getConnection();
        Statement createStatement = connection.createStatement();
        String sql = "select * from account;";
        ResultSet resultSet = createStatement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString(1)+"--"+resultSet.getString(2));
        }
        C3P0Utils.close(connection, createStatement, resultSet);
    }

}
