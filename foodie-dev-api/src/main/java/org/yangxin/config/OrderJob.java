package org.yangxin.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.yangxin.service.OrderService;
import org.yangxin.utils.DateUtil;

/**
 * 订单定时任务
 *
 * @author yangxin
 * 2020/11/22
 */
@Component
public class OrderJob {

    private final OrderService orderService;

    @Autowired
    public OrderJob(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 定时关闭未支付订单
     */
    @Scheduled(cron = "0 0 0/1 * * ?")
    public void autoCloseOrder() {
        orderService.closeOrder();

        //        System.out.println("执行定时任务，当前时间为：" + DateUtil.getCurrentDateString(DateUtil.DATETIME_PATTERN));
    }
}
