package testCron;

import org.apache.commons.lang3.ObjectUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.triggers.AbstractTrigger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

public class UseTriggerBuilder {
    public Date addOneScheduler(String jobName, String jobGroup, Class clazz, String cron, Date start, Date end, Map<String, Object> params, int misfire) throws SchedulerException {
        //获得定义的job
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName, jobGroup)
                .build();

        //定义触发器
        TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger()
                .withIdentity(jobName, jobGroup)
                .startAt(start)
                .endAt(end);

        if (ObjectUtils.isNotEmpty(cron)) {
            builder.withSchedule(CronScheduleBuilder.cronSchedule(cron));
            //每10秒触发一次
            //.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(10).repeatForever())
        }

        AbstractTrigger trigger = (AbstractTrigger) builder.build();
        trigger.setMisfireInstruction(misfire);

        //设置Job任务类和触发器
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        Date d = scheduler.scheduleJob(jobDetail, trigger);
        //启动定时器 SchedulerFactoryBean自动启动   用StdSchedulerFactory可以不自动start
        // scheduler.start();

        return d;
    }

    public List<Date> addScheduler(String jobGroup, String jobName, String taskId, List<String> crons) {
        List<Date> list = new ArrayList<>();
        try {
            //创建调度器，粘合工作和触发器
            Scheduler scheduler = new StdSchedulerFactory().getScheduler();


            //创建工作
            JobDetail jobDetail = JobBuilder.newJob(JobCase.class) // 任务执行类
                    .withDescription("工作的描述") //工作的描述
                    .withIdentity(jobName, jobGroup) //工作的名称/工作的组
                    .storeDurably()//没有绑定trigger时，继续保存在调度器中
                    .usingJobData(taskId, "taskId!!") //定义属性
                    .build();
            System.out.println(jobDetail.getKey());
            //trigger相关的builder

            scheduler.addJob(jobDetail, false);

            for (int i = 0; i < crons.size(); i++) {
                CronScheduleBuilder builder = CronScheduleBuilder.cronSchedule(crons.get(i));

                //创建触发器
                Trigger trigger = TriggerBuilder.newTrigger()
                        .forJob(jobDetail)
                        .withDescription("触发器的描述") //触发器的描述
                        .withIdentity("printTime321" + i, jobGroup)//触发器的名称/触发器的组
                        .withSchedule(builder) //定时任务执行时间
                        .startAt(new Date())//任务开始时间（不设置，默认为当前时间）
                        .build();

                System.out.println(trigger.getKey());
                Date date = scheduler.scheduleJob(trigger);
                list.add(date);
            }

            scheduler.start();
            //运行一段时间后关闭
//            Thread.sleep(10000);
//            scheduler.shutdown(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Date> addJobByCrones(String jobName, String jobGroup, Class clazz, List<String> crones, Map<String, Object> params, int misfire) throws SchedulerException {
        List<Date> dateList = new ArrayList<>();
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        //在定义触发器前先生成一个触发器开始时间
        //避免触发器开始时间太晚错过指定cron时间
        Date start = new Date();

        if (ObjectUtils.isEmpty(crones)) {
            //log.info("定时任务异常：cron表达式为空");
        }

        //获得定义的job
        JobDetail jobDetail = JobBuilder.newJob(clazz)
                .withIdentity(jobName, jobGroup)
                //没有绑定trigger时，继续保存在调度器中
                .storeDurably()
                .build();

        if (ObjectUtils.isNotEmpty(params)) {
            jobDetail.getJobDataMap().putAll(params);
        }

        //调度器加入job
        scheduler.addJob(jobDetail, false);

        //批量定义触发器
        int num = crones.size();
        for (int i = 0; i < num; i++) {
            TriggerBuilder<Trigger> builder = TriggerBuilder.newTrigger()
                    //多个触发器绑定job
                    .forJob(jobDetail)
                    .withIdentity(jobName + i, jobGroup)
                    .startAt(start);

            //逐条解析cron表达式
            builder.withSchedule(CronScheduleBuilder.cronSchedule(crones.get(i)));
            AbstractTrigger trigger = (AbstractTrigger) builder.build();
            trigger.setMisfireInstruction(misfire);
            //设置Job任务类和触发器
            Date date = scheduler.scheduleJob(trigger);

            dateList.add(date);
        }

        //启动定时器 SchedulerFactoryBean自动启动   用StdSchedulerFactory可以不自动start
        // scheduler.start();

        return dateList;
    }

    public static void main(String[] args) throws SchedulerException, ParseException {
        UseTriggerBuilder utb = new UseTriggerBuilder();

//        String cron = "0/3 * * * * ? *";
//        String group = "g1";
//        String name = "n1";
//        Date start = new Date();
//        Date end = start;
//        end.setTime(end.getTime() + 60 * 60 *1000);
//        int misfire = 2;
//        Map<String, Object> param = new HashMap<>();
//        param.put("triggertopic", "scheduler&mix");
//        param.put("name", "jim");
//        Date date = utb.addOneScheduler( "printTime", "100851", JobCase.class, "0/3 * * * * ? *", start, end, param, 2);
//        System.out.println(date + "-----------------------");

//        List<String> crons = new ArrayList<>();
//        crons.add("0/5 * * * * ? *");
//        crons.add("0/3 * * * * ? *");
//        crons.add("0/2 * * * * ? *");
//        List<Date> list = utb.addScheduler("group1", "printTime", "0011001", crons);
//        System.out.println("-------------------------" + list);

        String cron1 = "0/7 4 16 31 2 ? 2022";
        CronExpression ce = new CronExpression(cron1);
        Date nextValidTimeAfter = ce.getTimeAfter(new Date());
        System.out.println(nextValidTimeAfter);

        LocalDateTime l1 = LocalDateTime.now();
        LocalDateTime l2 = l1.plusDays(1);
        System.out.println(l1.compareTo(l2) < 0);

        System.out.println("=========================");
        String startTime = "2022-4-7 17:11:50";
        String endTime = "2022-4-7 17:12:30";
        String cron = "0/5 * * * * ?";
        List<LocalDateTime> limitDateList = new ArrayList<>();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        LocalDateTime currentTime = sdf.parse(startTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        LocalDateTime overTime = sdf.parse(endTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

        org.springframework.scheduling.support.CronExpression cronExpression = org.springframework.scheduling.support.CronExpression.parse(cron);
        LocalDateTime next = currentTime;
        int limitNum = 10;
        for (int i = 0; i < limitNum; i++) {
            assert next != null;
            next = cronExpression.next(next);
            if (overTime != null && next.compareTo(overTime) > 0) {
                break;
            }
            limitDateList.add(next);
        }
        System.out.println(limitDateList);
    }


}
