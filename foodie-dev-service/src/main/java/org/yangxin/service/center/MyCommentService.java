package org.yangxin.service.center;

import org.yangxin.pojo.OrderItems;
import org.yangxin.pojo.bo.center.OrderItemsCommentBO;
import org.yangxin.utils.PagedGridResult;

import java.util.List;

/**
 * @author yangxin
 * 2020/11/27 13:08
 */
public interface MyCommentService {

    /**
     * 根据订单Id查询关联的商品
     *
     * @param orderId 订单Id
     * @return 订单关联商品
     */
    List<OrderItems> queryPendingComment(String orderId);

    /**
     * 保存用户的评论
     *
     * @param orderId 订单Id
     * @param userId 用户Id
     * @param list 评论列表
     */
    void saveComments(String orderId, String userId, List<OrderItemsCommentBO> list);

    /**
     * 我的评价查询，分页
     *
     * @param userId 用户Id
     * @param page 开始页
     * @param pageSize 每页显示多少条
     */
    PagedGridResult queryMyComments(String userId, Integer page, Integer pageSize);
}
