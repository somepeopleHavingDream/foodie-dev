package org.yangxin.result;

import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * 分页
 *
 * @author yangxin
 * 2019/11/28 21:22
 */
@Data
@Builder
public class PagingGridResult {
    /**
     * 当前页号
     */
    private int page;

    /**
     * 总页数
     */
    private int total;

    /**
     * 总记录数
     */
    private long record;

    /**
     * 每行显示的内容
     */
    private List<?> rows;
}
