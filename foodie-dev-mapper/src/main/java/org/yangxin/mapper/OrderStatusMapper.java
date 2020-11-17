package org.yangxin.mapper;

import org.yangxin.pojo.OrderStatus;

public interface OrderStatusMapper {

    int deleteByPrimaryKey(String orderId);

    int insert(OrderStatus record);

    int insertSelective(OrderStatus record);

    OrderStatus selectByPrimaryKey(String orderId);

    int updateByPrimaryKeySelective(OrderStatus record);

    int updateByPrimaryKey(OrderStatus record);
}