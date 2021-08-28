package com.ssm._3_servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecondServlet extends HttpServlet {

	String encode="";

	public SecondServlet(){
		System.out.println("实例化：构造方法，默认的构造方法....");
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		//可以在初始化的时候读取Servlet的配置参数
		encode = config.getInitParameter("encoding");//获取配置的编码格式  UTF-8
		System.out.println(encode);
		System.out.println("初始化:Servlet初始化会自动调用这个方法....");
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset="+encode);
		System.out.println("服务:可以对外提供服务.....");
	}


	@Override
	public void destroy() {
		System.out.println("销毁:销毁的方法,我死了~~~");
	}

}
