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
    // 用户
    USERNAME_OR_PASSWORD_CANT_EMPTY(1, "用户名或者密码不能为空"),
    USERNAME_ALREADY_EXIST(2, "用户名已存在"),
    PASSWORD_LENGTH_INVALID(3, "密码长度不合规"),
    PASSWORD_CONFIRM_NOT_EQUAL(4, "两次密码输入不一致"),
    USERNAME_OR_PASSWORD_ERROR(5, "用户名或密码输入错误"),

    // 分类
    CATEGORY_IS_NOT_EXIST(6, "分类不存在"),

    // 参数
    PARAMETER_CANT_EMPTY(7, "参数不能为空"),
    PARAMETER_ERROR(8, "参数错误"),

    // 支付方式
    PAY_METHOD_NOT_SUPPORTED(9, "支付方式不支持"),

    // 库存
    DECREASE_STOCK_FAIL(10, "减库存失败");

    /**
     * 状态码
     */
    private final Integer code;

    /**
     * 提示信息
     */
    private final String message;
}
