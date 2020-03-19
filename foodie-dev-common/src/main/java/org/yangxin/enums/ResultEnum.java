package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果
 *
 * @author yangxin
 * 2019/11/13 22:18
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    // 用户(1.)
    USERNAME_OR_PASSWORD_CANT_EMPTY(10, "用户名或者密码不能为空"),
    USERNAME_ALREADY_EXIST(11, "用户名已存在"),
    PASSWORD_LENGTH_INVALID(12, "密码长度不合规"),
    PASSWORD_CONFIRM_NOT_EQUAL(13, "两次密码输入不一致"),
    USERNAME_OR_PASSWORD_ERROR(14, "用户名或密码输入错误"),

    // 分类(2.)
    CATEGORY_IS_NOT_EXIST(20, "分类不存在"),

    // 参数(3.)
    PARAMETER_CANT_EMPTY(30, "参数不能为空"),
    PARAMETER_ERROR(31, "参数错误"),

    // 支付方式(4.)
    PAY_METHOD_NOT_SUPPORTED(40, "支付方式不支持"),

    // 库存(5.)
    DECREASE_STOCK_FAIL(50, "减库存失败"),

    // 订单(6.)
    PAYMENT_CREATE_ORDER_FAIL(60, "支付中心订单创建失败，请联系管理员");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;
}
