package org.yangxin.pojo.bo.center;

import lombok.Data;

/**
 * @author yangxin
 * 2020/11/27 16:26
 */
@Data
public class OrderItemsCommentBO {

    private String commentId;

    private String itemId;

    private String itemName;

    private String itemSpecId;

    private String itemSpecName;

    private Integer commentLevel;

    private String content;
}
