package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 结果枚举类
 *
 * @author yangxin
 * 2019/11/13 22:18
 */
@AllArgsConstructor
@Getter
public enum ResultEnum {
    USERNAME_OR_PASSWORD_CANT_EMPTY(1, "用户名或者密码不能为空"),
    USERNAME_ALREADY_EXIST(2, "用户名已存在"),
    PASSWORD_LENGTH_INVALID(3, "密码长度不合规"),
    PASSWORD_CONFIRM_NOT_EQUAL(4, "两次密码输入不一致"),
    USERNAME_OR_PASSWORD_ERROR(5, "用户名或密码输入错误");

    private final Integer code;
    private final String message;
}
