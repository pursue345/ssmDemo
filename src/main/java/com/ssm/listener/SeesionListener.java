package com.ssm.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SeesionListener implements HttpSessionListener {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        logger.info("HttpSessionListener开始..."+session);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        logger.info("HttpSessionListener销毁..."+session);
    }
}
