package org.yangxin.mapper;

import org.apache.ibatis.annotations.Param;
import org.yangxin.pojo.Orders;
import org.yangxin.pojo.vo.order.MyOrderVO;

import java.util.List;
import java.util.Map;

public interface OrdersMapper {

    int deleteByPrimaryKey(String id);

    int insert(Orders record);

    int insertSelective(Orders record);

    Orders selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Orders record);

    int updateByPrimaryKey(Orders record);

    List<MyOrderVO> queryMyOrder(@Param("paramMap") Map<String, Object> map);

    Orders selectOne(Orders orders);

    int deleteOrder(Orders orders);
}