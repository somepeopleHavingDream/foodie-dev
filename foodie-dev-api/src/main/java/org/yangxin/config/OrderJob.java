package org.yangxin.config;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yangxin.utils.DateUtil;

/**
 * 订单定时任务
 *
 * @author yangxin
 * 2020/11/22
 */
@Component
public class OrderJob {

    @Scheduled(cron = "0/3 * * * * ?")
    public void autoCloseOrder() {
        System.out.println("执行定时任务，当前时间为：" + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }
}
