package org.yangxin.service.center;

import org.yangxin.pojo.Orders;
import org.yangxin.utils.PagedGridResult;

/**
 * @author yangxin
 * 2020/11/26 20:47
 */
public interface MyOrderService {

    /**
     * 查询我的订单列表
     */
    PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize);

    /**
     * 订单状态-->商家发货
     *
     * @param orderId 订单Id
     */
    void updateDeliverOrderStatus(String orderId);

    /**
     * 查询我的订单

     * @param userId 用户Id
     * @param orderId 订单Id
     * @return 我的订单
     */
    Orders queryMyOrder(String userId, String orderId);
}
