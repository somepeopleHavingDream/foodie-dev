package org.yangxin.exception.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.yangxin.pojo.vo.common.JSONVO;

/**
 * 自定义异常处理类
 *
 * @author yangxin
 * 2020/11/25 21:51
 */
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * 上传文件超过500k，捕获异常：MaxUploadSizeExceededException
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public JSONVO handleMaxUploadFile(MaxUploadSizeExceededException maxUploadSizeExceededException) {
        return JSONVO.errorMsg("文件上传大小不能超过500k，请压缩图片或者降低图片质量再上传。");
    }
}
