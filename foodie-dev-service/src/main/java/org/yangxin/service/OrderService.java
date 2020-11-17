package org.yangxin.service;

import org.yangxin.pojo.OrderStatus;
import org.yangxin.pojo.query.SubmitOrderQuery;

/**
 * 订单
 *
 * @author yangxin
 * 2019/12/06 10:43
 */
public interface OrderService {

    /**
     * 创建订单相关信息
     */
    String createOrder(SubmitOrderQuery submitOrderQuery);

    /**
     * 修改订单状态
     *
     * @param orderId 订单Id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单
     *
     * @param orderId　订单号
     * @return 订单状态
     */
    OrderStatus queryOrderStatusInfo(String orderId);
}
