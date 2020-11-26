package org.yangxin.utils;

import lombok.Data;

import java.util.List;

/**
 * 用来返回分析Grid的数据格式
 *
 * @author yangxin
 * 2020/11/26 20:50
 */
@Data
public class PagedGridResult {

    /**
     * 当前页数
     */
    private int page;

    /**
     * 总页数
     */
    private int total;

    /**
     * 总记录数
     */
    private long records;

    /**
     * 每行显示的内容
     */
    private List<?> rows;
}
