package org.yangxin.service;

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
}
