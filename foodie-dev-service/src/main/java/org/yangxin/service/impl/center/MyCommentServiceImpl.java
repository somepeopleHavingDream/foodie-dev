package org.yangxin.service.impl.center;

import com.github.pagehelper.PageHelper;
import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.mapper.ItemsCommentsMapper;
import org.yangxin.mapper.OrderItemsMapper;
import org.yangxin.mapper.OrderStatusMapper;
import org.yangxin.mapper.OrdersMapper;
import org.yangxin.pojo.Order;
import org.yangxin.pojo.OrderItems;
import org.yangxin.pojo.OrderStatus;
import org.yangxin.pojo.bo.center.OrderItemsCommentBO;
import org.yangxin.pojo.vo.comment.MyCommentVO;
import org.yangxin.service.BaseService;
import org.yangxin.service.center.MyCommentService;
import org.yangxin.utils.PagedGridResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangxin
 * 2020/11/27 13:15
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MyCommentServiceImpl extends BaseService implements MyCommentService {

    private final OrderItemsMapper orderItemsMapper;
    private final ItemsCommentsMapper itemsCommentsMapper;
    private final OrdersMapper ordersMapper;
    private final OrderStatusMapper orderStatusMapper;

    private final Sid sid;

    @Autowired
    public MyCommentServiceImpl(OrderItemsMapper orderItemsMapper, ItemsCommentsMapper itemsCommentsMapper, OrdersMapper ordersMapper, OrderStatusMapper orderStatusMapper, Sid sid) {
        this.orderItemsMapper = orderItemsMapper;
        this.itemsCommentsMapper = itemsCommentsMapper;
        this.ordersMapper = ordersMapper;
        this.orderStatusMapper = orderStatusMapper;
        this.sid = sid;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<OrderItems> queryPendingComment(String orderId) {
        OrderItems orderItems = OrderItems.builder()
                .orderId(orderId)
                .build();
        return orderItemsMapper.select(orderItems);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public void saveComments(String orderId, String userId, List<OrderItemsCommentBO> commentList) {
        // 1. 保存评价 items_comments
        for (OrderItemsCommentBO orderItemsCommentBO : commentList) {
            orderItemsCommentBO.setCommentId(sid.nextShort());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        map.put("commentList", commentList);
        itemsCommentsMapper.saveComments(map);

        // 2. 修改订单表，改为已评价 order
        Order order = Order.builder()
                .id(orderId)
                .isComment(YesNoEnum.YES.getType())
                .build();
        ordersMapper.updateByPrimaryKeySelective(order);

        // 3. 修改订单状态表的留言时间 order_status
        OrderStatus orderStatus = OrderStatus.builder()
                .orderId(orderId)
                .commentTime(new Date())
                .build();
        orderStatusMapper.updateByPrimaryKeySelective(orderStatus);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<MyCommentVO> myCommentVOList = itemsCommentsMapper.queryMyComments(map);

        return setPagedGrid(myCommentVOList, page);
    }
}