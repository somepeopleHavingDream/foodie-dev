package org.yangxin.pojo.vo.order;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 用户中心，我的订单列表
 *
 * @author yangxin
 * 2020/11/26
 */
@Data
public class MyOrderVO {

    private String orderId;

    private Date createdTime;

    private Integer payMethod;

    private Integer realPayAmount;

    private Integer postAmount;

    private Integer isComment;

    private Integer orderStatus;

    private List<MySubOrderItemVO> subOrderItemList;
}
