package org.yangxin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 支付方式
 *
 * @author yangxin
 * 2019/12/06 10:35
 */
@AllArgsConstructor
@Getter
public enum PayMethodEnum {
    /**
     * 微信支付
     */
    WEIXIN(1, "微信"),

    /**
     * 支付宝支付
     */
    ALIPAY(2, "支付宝");

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 值
     */
    private final String value;
}
