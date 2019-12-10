package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单状态
 *
 * @author yangxin
 * 2019/12/10 15:28
 */
@AllArgsConstructor
@Getter
public enum OrderStatusEnum {
    /**
     * 待付款
     */
    WAIT_PAY(10, "待付款"),

    /**
     * 已付款，待发货
     */
    WAIT_DELIVER(20, "已付款，待发货"),

    /**
     * 已发货，待收货
     */
    WAIT_RECEIVE(30, "已发货，待收货"),

    /**
     * 交易成功
     */
    SUCCESS(40, "交易成功"),

    /**
     * 交易关闭
     */
    CLOSE(50, "交易关闭");

    /**
     * 类别
     */
    private final Integer type;

    /**
     * 值
     */
    private final String value;
}
