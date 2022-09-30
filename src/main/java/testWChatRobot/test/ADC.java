package testWChatRobot.test;


import lombok.Data;
import org.apache.commons.lang3.ObjectUtils;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class ADC extends AD {
    public int attack;

    //public String ability;

    public int distance;

    public ADC(int attack, String ability, int distance) {
        this.attack = attack;
        super.ability = ability;
        //this.ability = ability;
        this.distance = distance;
    }

    public static void main(String[] args) throws ParseException {
        String cron = "0 0 0 1 1 ? *";
        List<String> limitDateList = new ArrayList<>();

        String startTime = "2022-09-28 15:40:41";
        String endTime = "2025-09-29 00:00:00";
        int limitNum = 10;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date currentTime = ObjectUtils.isEmpty(endTime) ? new Date() : sdf.parse(startTime);
        Date overTime = ObjectUtils.isEmpty(endTime) ? null : sdf.parse(endTime);

        CronExpression cronExpression = new CronExpression(cron);
        Date nodeTime = currentTime, next;
        for (int i = 0; i < limitNum; i++) {
            next = cronExpression.getNextValidTimeAfter(nodeTime);
            nodeTime = next;
            if (next == null) {
                break;
            }
            if (overTime != null &&
                    overTime.getTime() < next.getTime()) {
                break;
            }
            limitDateList.add(sdf.format(next));
        }
        System.out.println(limitDateList);

    }
}
