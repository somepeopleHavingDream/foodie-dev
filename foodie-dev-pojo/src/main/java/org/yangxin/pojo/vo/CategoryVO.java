package org.yangxin.pojo.vo;

import lombok.Data;

import java.util.List;

/**
 * 二级分类
 *
 * @author yangxin
 * 2019/11/26 14:39
 */
@Data
public class CategoryVO {
    /**
     * id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品类型
     */
    private String type;

    /**
     * 父级id
     */
    private Integer fatherId;

    /**
     * 三级分类VO集合，前端就是这个字段命名
     */
    private List<SubCategoryVO> subCatList;
}
