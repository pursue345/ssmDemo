package com.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 在springMvc.xml文件中扫描该类
 * SpringMvc拦截器，拦截响应乱码，并编码utf-8
 */
public class EncodeIntecerceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		logger.info("编码格式拦截器---afterCompletion");
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		logger.info("编码格式拦截器---postHandle");
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		logger.info("编码格式拦截器---preHandle");
		response.setContentType("text/html;charset=utf-8");
		return true;
	}

}
