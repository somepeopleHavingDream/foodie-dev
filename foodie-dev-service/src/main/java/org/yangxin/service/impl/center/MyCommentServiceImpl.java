package org.yangxin.service.impl.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.mapper.OrderItemsMapper;
import org.yangxin.pojo.OrderItems;
import org.yangxin.service.center.MyCommentService;

import java.util.List;

/**
 * @author yangxin
 * 2020/11/27 13:15
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MyCommentServiceImpl implements MyCommentService {

    private final OrderItemsMapper orderItemsMapper;

    @Autowired
    public MyCommentServiceImpl(OrderItemsMapper orderItemsMapper) {
        this.orderItemsMapper = orderItemsMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems orderItems = OrderItems.builder()
                .orderId(orderId)
                .build();
        return orderItemsMapper.select(orderItems);
    }
}
