package com.ssm.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在springMvc.xml文件中扫描该类
 * SpringMvc拦截器，模拟拦截 /user的访问，禁止通行
 */
public class LogerInterceptor implements HandlerInterceptor {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        String requestURI = httpServletRequest.getRequestURI();
        if(requestURI.indexOf("/user") >= 0){
            logger.info("日志拦截器拦截user---preHandle");
            //若是返回false，则会进行拦截
//            return false;
        }
        logger.info("日志拦截器放行非user---preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("日志拦截器---postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("日志拦截器---afterCompletion");
    }
}
