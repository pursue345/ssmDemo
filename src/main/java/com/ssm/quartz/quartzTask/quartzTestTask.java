package com.ssm.quartz.quartzTask;

import com.ssm.quartz.quartzJob.QuartzJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import static org.quartz.TriggerBuilder.newTrigger;


@RequestMapping
public class quartzTestTask {

    @ResponseBody
    public void test01() throws InterruptedException {
        try {
            System.out.println("定时任务你起来嘛...");
            // 创建scheduler
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            // 1.创建JobDetail
            JobDetail jobDetail = JobBuilder.newJob(QuartzJob.class).
                    withIdentity("job01","group1").
                    build();
            // 2.指定时间触发，每隔2s执行一次
            Trigger trigger2 = newTrigger()
                    .withIdentity("job01", "group1")
                    .startNow()
                    .withSchedule(CronScheduleBuilder.cronSchedule("0/2 * * * * ？*"))
                    .build();
            //(3)开启scheduler，执行任务，关闭scheduler
            scheduler.scheduleJob(jobDetail,trigger2);
            scheduler.start();
            Thread.sleep(10*1000L);
            scheduler.shutdown(true);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

}
