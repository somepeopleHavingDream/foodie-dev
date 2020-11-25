package org.yangxin.pojo.query;

import lombok.Data;

/**
 * 提交订单
 *
 * @author yangxin
 * 2019/12/06 10:26
 */
@Data
public class SubmitOrderBO {

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 商品规格Id
     */
    private String itemSpecIds;

    /**
     * 地址Id
     */
    private String addressId;

    /**
     * 支付方式
     */
    private Integer payMethod;

    /**
     * 买家留言
     */
    private String leftMsg;
}
