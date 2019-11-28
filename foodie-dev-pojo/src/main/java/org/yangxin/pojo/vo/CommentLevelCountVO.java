package org.yangxin.pojo.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 商品评价数量
 *
 * @author yangxin
 * 2019/11/28 10:00
 */
@Data
@Builder
public class CommentLevelCountVO {
    /**
     * 总评价数
     */
    private Integer totalCounts;

    /**
     * 好评数
     */
    private Integer goodCounts;

    /**
     * 中评数
     */
    private Integer normalCounts;

    /**
     * 差评数
     */
    private Integer badCounts;
}
