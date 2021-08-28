package com.ssm._1_jdbcutils;

import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Savepoint;

/**
 * 测试JdbcUtils
 */
public class JdbcTest {

    @Test
    public void test1() {
        Savepoint p = null;
        Connection conn = JdbcUtils.openConn();

        try {
            conn.setAutoCommit(false);
            String sql1 = "update account set money=money-100 where name='A'";
            PreparedStatement ps1 = conn.prepareStatement(sql1);
            //设置回滚点
            p = conn.setSavepoint();
            int count1 = ps1.executeUpdate();
            System.out.println("A的钱数减少100");

            int i = 100 / 0;
            String sql2 = "update account set money=money+100 where name='B'";
            PreparedStatement ps2 = conn.prepareStatement(sql2);
            int count2 = ps2.executeUpdate();
            System.out.println("B的钱数增加100");
        } catch (Exception var11) {
            try {
                conn.rollback(p);
                conn.commit();
            } catch (SQLException var10) {
                var10.printStackTrace();
            }
            var11.printStackTrace();
        }
    }

}
