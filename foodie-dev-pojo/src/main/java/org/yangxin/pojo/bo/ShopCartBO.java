package org.yangxin.pojo.bo;

import lombok.Data;

/**
 * 购物车
 *
 * @author yangxin
 * 2019/12/04 10:34
 */
@Data
public class ShopCartBO {

    /**
     * 商品Id
     */
    private String itemId;

    /**
     * 商品图片url
     */
    private String itemImgUrl;

    /**
     * 商品名称
     */
    private String itemName;

    /**
     * 规格Id
     */
    private String specId;

    /**
     * 规格名称
     */
    private String specName;

    /**
     * 购买的商品数量
     */
    private Integer buyCounts;

    /**
     * 折扣
     */
    private String priceDiscount;

    /**
     * 商品正常价格
     */
    private String priceNormal;
}
