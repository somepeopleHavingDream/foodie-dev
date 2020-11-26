package org.yangxin.pojo.vo.order;

import lombok.Data;

/**
 * 用户中心，我的订单列表嵌套商品VO
 *
 * @author yangxin
 * 2020/11/26 20:37
 */
@Data
public class MySubOrderItemVO {

    private String itemId;

    private String itemImg;

    private String itemName;

    private String itemSpecName;

    private Integer buyCounts;

    private Integer price;
}
