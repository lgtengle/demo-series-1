package com.lg.quartz;

import org.quartz.*;
import org.quartz.impl.JobDetailImpl;

import java.util.Date;

/**
 * <p>
 * description:
 * </p>
 * Created on 2017/10/11 17:42
 *
 * @author leiguang
 */
public class QuartzTest implements Job {

    public void execute(JobExecutionContext  context) throws JobExecutionException {
        System.out.println("Generating report - "
                + context.getJobDetail().getJobDataMap().get("type"));
        System.out.println(new Date().toString());
    }


    public static void main(String[] args) {
        try {
            // 创建一个Scheduler
            SchedulerFactory schedFact =
                    new org.quartz.impl.StdSchedulerFactory();
            Scheduler sched = schedFact.getScheduler();
            sched.start();
            // 创建一个JobDetail，指明name，groupname，以及具体的Job类名，
            //该Job负责定义需要执行任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzTest.class)
                    .withIdentity("myJob")
                    .build();
            jobDetail.getJobDataMap().put("type", "FULL");
            // 创建一个每周触发的Trigger，指明星期几几点几分执行
            Trigger trigger = TriggerBuilder.newTrigger()
                    .withIdentity("test", "myGroup")
                    .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(10))
                    .startAt(new Date())
                    .build();

            // 用scheduler将JobDetail与Trigger关联在一起，开始调度任务
            sched.scheduleJob(jobDetail, trigger);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
