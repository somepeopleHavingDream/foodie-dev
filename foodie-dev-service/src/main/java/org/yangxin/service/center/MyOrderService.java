package org.yangxin.service.center;

import org.yangxin.pojo.Orders;
import org.yangxin.pojo.vo.order.OrderStatusCountsVO;
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

    /**
     * 更新订单状态->确认收货
     *
     * @param orderId 订单Id
     * @return 是否更新成功
     */
    boolean updateReceiveOrderStatus(String orderId);

    /**
     * 删除订单（逻辑删除）
     *
     * @param userId 用户Id
     * @param orderId 订单Id
     * @return 是否删除
     */
    boolean deleteOrder(String userId, String orderId);

    /**
     * 查询用户订单数
     *
     * @param userId 用户Id
     * @return 用户订单数据
     */
    OrderStatusCountsVO getOrderStatusCounts(String userId);

    /**
     * 获取分页的订单动向
     *
     * @param userId 用户Id
     * @param page 第几页
     * @param pageSize 每页显示的条数
     * @return 分页对象
     */
    PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize);
}
