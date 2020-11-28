package org.yangxin.mapper;

import org.yangxin.pojo.OrderItem;

import java.util.List;

public interface OrderItemsMapper {

    int deleteByPrimaryKey(String id);

    int insert(OrderItem record);

    int insertSelective(OrderItem record);

    OrderItem selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderItem record);

    int updateByPrimaryKey(OrderItem record);

    List<OrderItem> select(OrderItem orderItem);
}