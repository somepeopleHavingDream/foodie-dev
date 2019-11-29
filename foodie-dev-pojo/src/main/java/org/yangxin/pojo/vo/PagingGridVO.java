package org.yangxin.pojo.vo;

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
public class PagingGridVO {
    /**
     * 当前页号
     */
    private Integer page;

    /**
     * 总页数
     */
    private Integer total;

    /**
     * 总记录数
     */
    private Long records;

    /**
     * 每行显示的内容
     */
    private List<?> rows;
}
