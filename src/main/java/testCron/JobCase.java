package testCron;

import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class JobCase implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail detail = jobExecutionContext.getJobDetail();
        String taskId = detail.getJobDataMap().getString("taskId") + UUID.randomUUID();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

        System.out.println(taskId + "==================" + sdf.format(new Date()));
    }
}
