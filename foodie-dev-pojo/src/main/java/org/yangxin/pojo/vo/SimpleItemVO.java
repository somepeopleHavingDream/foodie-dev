package org.yangxin.pojo.vo;

import lombok.Data;

/**
 * 6个商品
 *
 * @author yangxin
 * 2019/11/26 21:57
 */
@Data
class SimpleItemVO {
    /**
     * 商品Id
     */
    private String itemId;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 商品图片地址
     */
    private String itemURL;
}
