package cn.zys.quartzs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @program: road-health
 * @description: 注解开发
 * @author: xiaozhang6666
 * @create: 2020-09-27 10:23
 **/
@Component
public class TestQAnno {

    //    @Scheduled(cron = "*/5 * * * * SUN-MON")
    @Scheduled(cron = "0/10 * * * * ?")
    public void test() {
        System.out.println("计时任务....");
    }
}
