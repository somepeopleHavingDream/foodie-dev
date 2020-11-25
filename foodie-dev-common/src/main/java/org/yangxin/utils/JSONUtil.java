package org.yangxin.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json
 *
 * @author yangxin
 * 2019/11/21 21:01
 */
public final class JSONUtil {

//    private static final Gson gson = new Gson();

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


    /**
     * 对象转Json字符串
     */
    public static <T> String obj2String(T src) {
        try {
            return OBJECT_MAPPER.writeValueAsString(src);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
//        return gson.toJson(src);
    }
}
