package com.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * 监听器是典型的观察者设计模式的实现
 *
 * Servlet和spring中我们熟知的listeners包括HttpSessionListener、ServletContextListener、ApplicationListener
 * HttpSessionListener是对javax.servlet.http.HttpSession（session）的监听
 * ServletContextListener是对javax.servlet.ServletContext(application)的监听
 * ApplicationListener是对Spring的ApplicationContext的监听。
 *
 * 监听器ServletContextListener
 * 它能够监听 ServletContext 对象的生命周期，实际上就是监听 Web 应用的生命周期。
 */

/**
 * 两种配置 @WebListener与xml配置
 * 在web.xml配置
 * <listener>
 *    <listener-class>com.ss.listener.ServletLister</listener-class>
 * </listener>
 */
@WebListener
public class ServletLister implements ServletContextListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    //当Servlet 容器启动Web 应用时调用该方法。在调用完该方法之后，容器再对Filter 初始化，并且对那些在Web 应用启动时就需要被初始化的Servlet 进行初始化。
    public void contextInitialized(ServletContextEvent sce) {
        logger.info("ServletContextListener监听器初始化....");
    }

    @Override
    //当Servlet 容器终止Web 应用时调用该方法。在调用该方法之前，容器会先销毁所有的Servlet 和Filter 过滤器。
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("ServletContextListener监听器销毁...");
    }
}
