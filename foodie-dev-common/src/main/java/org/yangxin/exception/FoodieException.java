package org.yangxin.exception;

import org.yangxin.enums.ResultEnum;

/**
 * 商城
 *
 * @author yangxin
 * 2019/12/10 17:50
 */
public class FoodieException extends RuntimeException {
    private Integer code;

    public FoodieException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public  FoodieException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
