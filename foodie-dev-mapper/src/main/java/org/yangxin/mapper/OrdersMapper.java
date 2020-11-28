package org.yangxin.mapper;

import org.apache.ibatis.annotations.Param;
import org.yangxin.pojo.Order;
import org.yangxin.pojo.OrderStatus;
import org.yangxin.pojo.vo.order.MyOrderVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {

    int deleteByPrimaryKey(String id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<MyOrderVO> queryMyOrder(@Param("paramMap") Map<String, Object> map);

    Order selectOne(Order order);

    int deleteOrder(Order order);

    int getMyOrderStatusCounts(@Param("paramMap") Map<String, Object> map);

    List<OrderStatus> getMyOrderTrend(@Param("paramMap") Map<String, Object> map);
}