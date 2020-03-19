package org.yangxin.service;

import org.yangxin.pojo.query.SubmitOrderQuery;
import org.yangxin.pojo.vo.order.OrderVO;

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
    OrderVO createOrder(SubmitOrderQuery submitOrderQuery);

    /**
     * 修改订单状态
     *
     * @param orderId 订单Id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);
}
