package com.ssm._3_servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LoginServlet.do")
public class LoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8"); //处理所有POST请求参数的乱码
        response.setContentType("text/html;charset=utf-8");  //设置浏览器的响应乱码
        //接收表单的参数，请求被封装到request对象上，获取参数 使用request对象
        String name=request.getParameter("username"); //根据标签的name属性值，获取该标签value值
        String pass=request.getParameter("userpass");
        System.out.println("输入的姓名是:"+name);
        System.out.println("输入的密码是:"+pass);

        //查询数据库 是否存在该用户   模拟   账号: 八戒  密码: 4321
        if("八戒".equals(name)&&"4321".equals(pass)){

            response.sendRedirect("index.html"); //重定向跳转
            request.getRequestDispatcher("index.html").forward(request, response);//转发跳转

        }else{
            response.getWriter().write("<script>alert('账号或者密码错误！');location.href='login.html';</script>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

}
