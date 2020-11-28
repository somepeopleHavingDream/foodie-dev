package org.yangxin.service.impl.center;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.OrderStatusEnum;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.mapper.OrderStatusMapper;
import org.yangxin.mapper.OrdersMapper;
import org.yangxin.pojo.Order;
import org.yangxin.pojo.OrderStatus;
import org.yangxin.pojo.vo.order.MyOrderVO;
import org.yangxin.pojo.vo.order.OrderStatusCountsVO;
import org.yangxin.service.BaseService;
import org.yangxin.service.center.MyOrderService;
import org.yangxin.utils.PagedGridResult;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yangxin
 * 2020/11/26 20:54
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class MyOrderServiceImpl extends BaseService implements MyOrderService {

    private final OrdersMapper ordersMapper;
    private final OrderStatusMapper orderStatusMapper;

    @Autowired
    public MyOrderServiceImpl(OrdersMapper ordersMapper, OrderStatusMapper orderStatusMapper) {
        this.ordersMapper = ordersMapper;
        this.orderStatusMapper = orderStatusMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult queryMyOrders(String userId, Integer orderStatus, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if (orderStatus != null) {
            map.put("orderStatus", orderStatus);
        }

        PageHelper.startPage(page, pageSize);
        List<MyOrderVO> list = ordersMapper.queryMyOrder(map);

        return setPagedGrid(list, page);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDeliverOrderStatus(String orderId) {
        OrderStatus orderStatus = OrderStatus.builder()
                .orderStatus(OrderStatusEnum.WAIT_RECEIVE.getType())
                .deliverTime(new Date())
                .orderId(orderId)
                .build();

        orderStatusMapper.updateDeliverOrderStatus(orderStatus);
    }

//    private PagedGridResult setPagedGrid(List<MyOrderVO> list, Integer page) {
//        PageInfo<MyOrderVO> pageList = new PageInfo<>(list);
//
//        PagedGridResult pagedGridResult = new PagedGridResult();
//        pagedGridResult.setPage(page);
//        pagedGridResult.setRows(list);
//        pagedGridResult.setTotal(pageList.getPages());
//        pagedGridResult.setRecords(pageList.getTotal());
//        return pagedGridResult;
//    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Order queryMyOrder(String userId, String orderId) {
        Order order = Order.builder()
                .userId(userId)
                .id(orderId)
                .isDelete(YesNoEnum.NO.getType())
                .build();
        return ordersMapper.selectOne(order);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean updateReceiveOrderStatus(String orderId) {
        OrderStatus orderStatus = OrderStatus.builder()
                .orderStatus(OrderStatusEnum.SUCCESS.getType())
                .successTime(new Date())
                .orderId(orderId)
                .build();

        return orderStatusMapper.updateReceiveOrderStatus(orderStatus) == 1;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean deleteOrder(String userId, String orderId) {
        Order order = Order.builder()
                .isDelete(YesNoEnum.YES.getType())
                .updatedTime(new Date())
                .userId(userId)
                .id(orderId)
                .build();
        return ordersMapper.deleteOrder(order) == 1;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public OrderStatusCountsVO getOrderStatusCounts(String userId) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        map.put("orderStatus", OrderStatusEnum.WAIT_PAY.getType());
        int waitPayCounts = ordersMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_DELIVER.getType());
        int waitDeliverCounts = ordersMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.WAIT_RECEIVE.getType());
        int waitReceiveCounts = ordersMapper.getMyOrderStatusCounts(map);

        map.put("orderStatus", OrderStatusEnum.SUCCESS.getType());
        map.put("isComment", YesNoEnum.NO.getType());
        int waitCommentCounts = ordersMapper.getMyOrderStatusCounts(map);

        return new OrderStatusCountsVO(waitPayCounts, waitDeliverCounts, waitReceiveCounts, waitCommentCounts);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagedGridResult getOrdersTrend(String userId, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        PageHelper.startPage(page, pageSize);
        List<OrderStatus> orderStatusList = ordersMapper.getMyOrderTrend(map);

        return setPagedGrid(orderStatusList, page);
    }
}
