package org.yangxin.pojo.vo.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 订单状态概览数量
 *
 * @author yangxin
 * 2020/11/27 20:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderStatusCountsVO {

    private Integer waitPayCounts;

    private Integer waitDeliverCounts;

    private Integer waitReceiveCounts;

    private Integer waitCommentCounts;
}
