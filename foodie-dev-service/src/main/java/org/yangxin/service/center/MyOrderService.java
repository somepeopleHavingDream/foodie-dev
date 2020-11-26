package org.yangxin.service.center;

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
}
