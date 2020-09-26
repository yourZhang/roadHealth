package cn.zys.config;

import cn.zys.quartzs.ClearImageJob;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @program: road-health
 * @description: 定时任务配置
 * @author: xiaozhang6666
 * @create: 2020-09-25 21:15
 **/
@Configuration
public class QuartzConfig {
    //<!--注册一个定义任务对象-->
    @Bean
    public ClearImageJob clearImageJob() {
        ClearImageJob clearImageJob = new ClearImageJob();
        return clearImageJob;
    }

    //<!-- 注册JobDetail,作用是负责通过反射调用指定的Job -->
    @Bean
    public MethodInvokingJobDetailFactoryBean clearImgJobDetail(ClearImageJob clearImageJob) {
        MethodInvokingJobDetailFactoryBean clearImgJobDetail = new MethodInvokingJobDetailFactoryBean();
        clearImgJobDetail.setTargetObject(clearImageJob);
        clearImgJobDetail.setTargetMethod("clearImagesJob");
        return clearImgJobDetail;
    }

    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean clearImgJobDetail) {
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        cronTriggerFactoryBean.setJobDetail(clearImgJobDetail.getObject());
//             <!-- 每隔10秒执行一次任务 0/10 * * * * ? -->
//            <!-- 每隔2分钟执行一次任务  0 0/2 * * * ? -->
//            <!-- 每天凌晨2点执行一次任务 0 0 2 * * ?  -->
//            <value>0/10 * * * * ?</value>
        cronTriggerFactoryBean.setCronExpression("0/10 * * * * ?");
        return cronTriggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        return schedulerFactoryBean;
    }
}
