package org.yangxin.service;

import org.yangxin.pojo.OrderStatus;
import org.yangxin.pojo.bo.SubmitOrderBO;
import org.yangxin.pojo.vo.order.OrderVO;

/**
 * 订单
 *
 * @author yangxin
 * 2019/12/06 10:43
 */
@SuppressWarnings("AlibabaAbstractMethodOrInterfaceMethodMustUseJavadoc")
public interface OrderService {

    /**
     * 创建订单相关信息
     */
    OrderVO createOrder(SubmitOrderBO submitOrderBO);

    /**
     * 修改订单状态
     *
     * @param orderId 订单Id
     * @param orderStatus 订单状态
     */
    void updateOrderStatus(String orderId, Integer orderStatus);

    /**
     * 查询订单状态
     *
     * @param orderId　订单号
     * @return 订单状态
     */
    OrderStatus queryOrderStatusInfo(String orderId);

    /**
     * 关闭超时未支付订单
     */
    void closeOrder();
}
