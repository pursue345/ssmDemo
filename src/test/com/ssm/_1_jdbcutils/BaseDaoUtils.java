package com.ssm._1_jdbcutils;

import java.sql.*;

/**
 * jdbc原生连接工具类
 */
public class BaseDaoUtils {

    Connection conn = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    // 1.打开数据库
    private void openConn() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/ssm?useUnicode=true&characterEncoding=utf-8",
                    "root", "123456");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 2.关闭数据库
    public void closeConn() {
        try {
            // 关闭资源
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 3.通用执行查询
    // String sql="select * from singer where age>? and birthday>?";
    // Object[] parms={18,'2010-10-10'};
    public ResultSet query(String sql, Object[] parms) {
        openConn();
        try {
            ps = conn.prepareStatement(sql);
            if(parms!=null){
                for (int i = 0; i < parms.length; i++) {
                    ps.setObject(i + 1, parms[i]);
                }
            }
            return ps.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            //closeConn(); //查询暂时不关数据库
        }
        return null;
    }

    // 4.执行增删改
    public int update(String sql,Object[] parms){
        openConn();
        try {
            ps = conn.prepareStatement(sql);
            if(parms!=null){
                for (int i = 0; i < parms.length; i++) {
                    ps.setObject(i + 1, parms[i]);
                }
            }
            return ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            closeConn();
        }
        return 0;
    }
}


