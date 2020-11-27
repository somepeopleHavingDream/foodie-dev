package org.yangxin.service.center;

import org.yangxin.pojo.OrderItems;

import java.util.List;

/**
 * @author yangxin
 * 2020/11/27 13:08
 */
public interface MyCommentService {

    /**
     * 根据订单Id查询关联的商品
     *
     * @param orderId 订单Id
     * @return 订单关联商品
     */
    List<OrderItems> queryPendingComment(String orderId);
}
