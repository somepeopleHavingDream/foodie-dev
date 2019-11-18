package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举类
 *
 * @author yangxin
 * 2019/11/18 21:15
 */
@AllArgsConstructor
@Getter
public enum SexEnum {
    WOMAN(0, "女"),
    MAN(1, "男"),
    SECRET(2, "保密");

    /**
     * 类别
     */
    private final Integer type;

    /**
     * 值
     */
    private final String value;
}
