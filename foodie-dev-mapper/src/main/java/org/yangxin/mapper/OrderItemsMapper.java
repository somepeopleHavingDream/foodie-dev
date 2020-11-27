package org.yangxin.mapper;

import org.yangxin.pojo.OrderItems;

import java.util.List;

public interface OrderItemsMapper {

    int deleteByPrimaryKey(String id);

    int insert(OrderItems record);

    int insertSelective(OrderItems record);

    OrderItems selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(OrderItems record);

    int updateByPrimaryKey(OrderItems record);

    List<OrderItems> select(OrderItems orderItems);
}