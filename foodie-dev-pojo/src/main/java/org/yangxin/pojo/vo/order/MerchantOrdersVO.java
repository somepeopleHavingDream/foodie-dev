package org.yangxin.pojo.vo.order;

import lombok.Builder;
import lombok.Data;

/**
 * 商户订单
 *
 * @author yangxin
 * 2020/03/19 20:41
 */
@Data
@Builder
public class MerchantOrdersVO {

    /**
     * 商户订单号
     */
    private String merchantOrderId;

    /**
     * 商户方的发起用户的用户主键id
     */
    private String merchantUserId;

    /**
     * 实际支付总金额（包含商户所支付的订单邮费总额）
     */
    private Integer amount;

    /**
     * 支付方式 1：微信 2：支付宝
     */
    private Integer payMethod;

    /**
     * 支付成功后的回调地址（自定义）
     */
    private String returnUrl;
}
