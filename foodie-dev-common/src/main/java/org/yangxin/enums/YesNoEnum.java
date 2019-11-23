package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否
 *
 * @author yangxin
 * 2019/11/23 16:01
 */
@AllArgsConstructor
@Getter
public enum YesNoEnum {
    NO(0, "否"),
    YES(1, "是");

    /**
     * 类别
     */
    private final Integer type;

    /**
     * 值
     */
    private final String value;
}
