package org.yangxin.utils;

import org.springframework.util.StringUtils;

/**
 * 通用脱敏
 * 可用于：用户名、手机号、邮箱、地址等
 *
 * @author yangxin
 * 2019/11/29 11:19
 */
public class DesensitizationUtil {
    /**
     * 脱敏长度
     */
    private static final Integer SIZE = 6;

    /**
     * 脱敏符号
     */
    private static final String SYMBOL = "*";

    /**
     * 通用脱敏方法
     */
    public static String commonDisplay(String value) {
        if (null == value || "".equals(value)) {
            return value;
        }
        int len = value.length();
        int p1 = len / 2;
        int p2 = p1 - 1;
        int p3 = len % 2;
        StringBuilder stringBuilder = new StringBuilder();
        if (len <= 2) {
            if (p3 == 1) {
                return SYMBOL;
            }
            stringBuilder.append(SYMBOL);
            stringBuilder.append(value.charAt(len - 1));
        } else {
            if (p2 <= 0) {
                stringBuilder.append(value, 0, 1);
                stringBuilder.append(SYMBOL);
                stringBuilder.append(value, len - 1, len);

            } else if (p2 >= SIZE / 2 && SIZE + 1 != len) {
                int p5 = (len - SIZE) / 2;
                stringBuilder.append(value, 0, p5);
                for (int i = 0; i < SIZE; i++) {
                    stringBuilder.append(SYMBOL);
                }
                if ((p3 == 0 && SIZE / 2 == 0) || (p3 != 0 && SIZE % 2 != 0)) {
                    stringBuilder.append(value, len - p5, len);
                } else {
                    stringBuilder.append(value, len - (p5 + 1), len);
                }
            } else {
                int p4 = len - 2;
                stringBuilder.append(value, 0, 1);
                for (int i = 0; i < p4; i++) {
                    stringBuilder.append(SYMBOL);
                }
                stringBuilder.append(value, len - 1, len);
            }
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String name = commonDisplay("慕课网");
        String mobile = commonDisplay("13900000000");
        String mail = commonDisplay("admin@imooc.com");
        String address = commonDisplay("北京大运河东路888号");

        System.out.println(name);
        System.out.println(mobile);
        System.out.println(mail);
        System.out.println(address);
    }
}
