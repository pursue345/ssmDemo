package com.ssm._1_jdbcutils;

import org.junit.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BaseDaoUtilsTest extends BaseDaoUtils {
    @Test
    public void test1() {
        String name = "C";
        String sql="select * from account where name=?";
        Object[] parms={name};
        ResultSet rs = this.query(sql, parms);
        try {
            if(rs.next()){
                System.out.println("查询到了");
            }else{
                System.out.println("没查到");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            this.closeConn();
        }
    }
}
