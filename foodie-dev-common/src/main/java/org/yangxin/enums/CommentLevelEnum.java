package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 评价等级
 *
 * @author yangxin
 * 2019/11/28 10:08
 */
@AllArgsConstructor
@Getter
public enum CommentLevelEnum {
    GOOD(1, "好评"),
    NORMAL(2, "中评"),
    BAD(3, "差评");

    private final Integer type;
    private final String value;
}
