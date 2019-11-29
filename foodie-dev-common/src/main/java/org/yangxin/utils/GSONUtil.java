package org.yangxin.utils;

import com.google.gson.Gson;

/**
 * json
 *
 * @author yangxin
 * 2019/11/21 21:01
 */
public final class GSONUtil {
    private static final Gson gson = new Gson();

    /**
     * 对象转Json字符串
     */
    public static <T> String obj2String(T src) {
        return gson.toJson(src);
    }
}
