package com.ssm.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 过滤器
 * web.xml可以配置该类，也可以通过WebFilter注解配置
 *
 * 拦截器与过滤器区别
 * 1.过滤器执行完交给拦截器和控制器  执行顺序（类栈：先进后出）
 * 2.两者都可以传递请求
 * 3.拦截器不依赖于servlet容器，过滤器依赖于servlet容器
 * 4.一个是容器支持一个是框架支持
 * 5.拦截器可以获取IOC容器中的各个bean，而过滤器就不行，这点很重要，在拦截器里注入一个service，可以调用业务逻辑。因为bean是spring框架里面的
 * 6.拦截器是基于java的反射机制的，而过滤器是基于函数回调。
 */
@WebFilter(filterName = "UserFilter",urlPatterns = "/user/*")
public class UserFilter implements Filter {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("user过滤器初始化一次...");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest)request;
        String requestURI = request1.getRequestURI();
        if(requestURI.indexOf("user") >=0 ){
            logger.info("user过滤器生效啦...");
        }
        logger.info("user过滤器---doFilter（）...");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        logger.info("user过滤器销毁...");
    }
}
