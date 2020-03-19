package org.yangxin.controller;

import org.springframework.stereotype.Controller;

/**
 * Controller父类
 */
@Controller
public class BaseController {
    public static final String FOODIE_SHOP_CART = "shopcart";

    /**
     * 评价页面条数
     */
    public static final int COMMENT_PAGE_SIZE = 10;

    /**
     * 支付中心的调用地址
     */
    public static final String paymentURL = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    /**
     *微信支付成功 -> 支付中心 -> 天天吃货平台
     *                     |-> 回调通知的url
     */
    public static final String payReturnURL = "http://localhost:8088/foodie-dev-api/orders/notifyMerchantOrderPaid";
}
