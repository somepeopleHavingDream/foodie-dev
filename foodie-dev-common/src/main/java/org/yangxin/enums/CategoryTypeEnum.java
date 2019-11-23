package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 分类类型
 *
 * @author yangxin
 * 2019/11/23 16:24
 */
@AllArgsConstructor
@Getter
public enum CategoryTypeEnum {
    _1_CATEGORY(1, "一级分类");
    /**
     * 类别
     */
    private final Integer type;

    /**
     * 值
     */
    private final String value;
}
