package org.yangxin.service.impl.center;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.OrderStatusEnum;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.mapper.OrderStatusMapper;
import org.yangxin.mapper.OrdersMapper;
import org.yangxin.pojo.OrderStatus;
import org.yangxin.pojo.Orders;
import org.yangxin.pojo.vo.order.MyOrderVO;
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
public class MyOrderServiceImpl implements MyOrderService {

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

    private PagedGridResult setPagedGrid(List<MyOrderVO> list, Integer page) {
        PageInfo<MyOrderVO> pageList = new PageInfo<>(list);

        PagedGridResult pagedGridResult = new PagedGridResult();
        pagedGridResult.setPage(page);
        pagedGridResult.setRows(list);
        pagedGridResult.setTotal(pageList.getPages());
        pagedGridResult.setRecords(pageList.getTotal());
        return pagedGridResult;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Orders queryMyOrder(String userId, String orderId) {
        Orders orders = Orders.builder()
                .userId(userId)
                .id(orderId)
                .isDelete(YesNoEnum.NO.getType())
                .build();
        return ordersMapper.selectOne(orders);
    }
}
