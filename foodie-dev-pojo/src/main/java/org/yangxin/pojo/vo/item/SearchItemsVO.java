package org.yangxin.pojo.vo.item;

import lombok.Data;

/**
 * 商品搜索
 *
 * @author yangxin
 * 2019/11/29 11:54
 */
@Data
public class SearchItemsVO {
    /**
     * 商品Id
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 销售量
     */
    private Integer sellCounts;

    /**
     * 图片地址
     */
    private String imgUrl;

    /**
     * 价格（以分为单位）
     */
    private Integer price;
}
