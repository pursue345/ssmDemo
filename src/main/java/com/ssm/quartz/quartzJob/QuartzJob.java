package com.ssm.quartz.quartzJob;

import jdk.nashorn.internal.objects.annotations.Setter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuartzJob implements Job {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private volatile int count = 0;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        System.out.println("我执行了，时间是" + System.currentTimeMillis() + ",count = " + count);
        count++;
    }
}
