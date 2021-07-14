package com.ssm.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 过滤器
 * web.xml可以配置该类，也可以通过WebFilter注解配置
 */
@WebFilter(filterName = "EncodingFilter",urlPatterns = "*.jsp")
public class EncodingFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("初始化过滤器EncodingFilter...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //设置请求响应乱码格式
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        logger.info("过滤器EncodingFilter编码格式...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        logger.info("过滤器EncodingFilter编码格式销毁了...");
    }
}
