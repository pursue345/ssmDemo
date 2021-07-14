package com.ssm.quartz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssm.pojo.User;
import com.ssm.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Spring3.0以后自带的task定时任务
 * core
 * 字段	允许值	允许的特殊字符
 * 秒（Seconds）	0~59的整数	, - * /    四个字符
 * 分（Minutes）	0~59的整数	, - * /    四个字符
 * 小时（Hours）	0~23的整数	, - * /    四个字符
 * 日期（DayofMonth）	1~31的整数（但是你需要考虑你月的天数）	,- * ? / L W C     八个字符
 * 月份（Month）	1~12的整数或者 JAN-DEC	, - * /    四个字符
 * 星期（DayofWeek）	1~7的整数或者 SUN-SAT （1=SUN）	, - * ? / L C #     八个字符
 * 年(可选，留空)（Year）	1970~2099	, - * /    四个字符
 */
@Component
@Lazy(value = false)
public class springTaskQuartz {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private volatile int count = 0;

    @Resource
    private UserService userService;

//    @Scheduled(cron="*/5 * * * * ?")
    public void queryUserById(){
        logger.info("springTaskQuartz定时任务执行第"+ count++ +"次");
        User user = userService.queryById(1);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            logger.info(objectMapper.writeValueAsString(user));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.info("jackSon转换异常...");
        }
    }

}
