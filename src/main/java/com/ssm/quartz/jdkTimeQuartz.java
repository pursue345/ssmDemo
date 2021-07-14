package com.ssm.quartz;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务
 * Java自带的java.util.Timer类
 */
public class jdkTimeQuartz extends TimerTask {

    @Override
    public void run() {
        System.out.println("java自带定时任务...");
    }

    public static void main(String[] args) {
        jdkTimeQuartz jdkTimeQuartz = new jdkTimeQuartz();
        Timer timer = new Timer();
        //延迟一秒执行一次
        timer.schedule(jdkTimeQuartz,0,1000);
    }
}
