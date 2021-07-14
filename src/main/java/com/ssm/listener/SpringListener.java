package com.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Application Event下 接口是ApplicationContextEvent
 * 该接口下，存在四个内置接口
 *      |-ContextClosedEvent：应用关闭事件
 *     |-ContextRefreshedEvent：应用刷新事件
 *     |-ContextStartedEvent：应用开启事件
 *     |-ContextStoppedEvent：应用停止事件
 */
@Component
public class SpringListener implements ApplicationListener<ApplicationEvent> {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        logger.info("ApplicationListener事件1...");
        if (applicationEvent instanceof ContextRefreshedEvent){
            ContextRefreshedEvent c = (ContextRefreshedEvent)applicationEvent;
            logger.info("spring容器装载完成获取bean对象{}....",c);
        }
        logger.info("ApplicationListener事件2...");
    }
}
