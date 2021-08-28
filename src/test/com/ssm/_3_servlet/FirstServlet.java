package com.ssm._3_servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 * HttpServlet抽象类
 */
public class FirstServlet extends HttpServlet {

	/*
	 * 处理用户发送的get请求:
	 * 1.直接在浏览器地址输入网址
	 * 2.在表单中使用  method="get"
	 * 3.点击超链接地址
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charSet=utf-8"); //设置响应头让浏览器支持网页UTF-8编码   ContentType="text/html;charSet=utf-8"
		PrintWriter out = response.getWriter(); //获取输出对象
		out.write("这是一个响应信息！！！");
		out.flush();
		out.close();
	}
}
