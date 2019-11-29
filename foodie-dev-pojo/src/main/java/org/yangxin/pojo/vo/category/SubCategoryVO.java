package org.yangxin.pojo.vo.category;

import lombok.Data;

/**
 * 三级分类
 *
 * @author yangxin
 * 2019/11/26 14:42
 */
@Data
class SubCategoryVO {
    /**
     * id
     */
    private Integer subId;

    /**
     * 商品名
     */
    private String subName;

    /**
     * 商品类型
     */
    private String subType;

    /**
     * 父级id
     */
    private Integer subFatherId;
}
