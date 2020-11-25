package org.yangxin.controller;

import org.springframework.stereotype.Controller;

import java.io.File;

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
    public static final String PAYMENT_URL = "http://payment.t.mukewang.com/foodie-payment/payment/createMerchantOrder";

    /**
     * 微信支付成功 -> 支付中心 -> 天天吃货平台
     * |-> 回调通知的url
     */
    public static final String PAY_RETURN_URL = "http://localhost:8088/foodie-dev-api/orders/notifyMerchantOrderPaid";

    /**
     * 用户上传头像的位置
     */
    public static final String IMAGE_USER_FACE_LOCATION = File.separator + "home" +
            File.separator + "yangxin" +
            File.separator + "Projects" +
            File.separator + "foodie-store";
}
