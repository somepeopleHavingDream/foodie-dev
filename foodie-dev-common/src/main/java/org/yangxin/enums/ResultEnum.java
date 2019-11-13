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
    USERNAME_CANT_EMPTY(1, "用户名不能为空"),
    USERNAME_ALREADY_EXIST(2, "用户名已存在");

    private final Integer code;
    private final String message;
}
