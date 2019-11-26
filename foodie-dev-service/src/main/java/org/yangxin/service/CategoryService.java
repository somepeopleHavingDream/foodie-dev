package org.yangxin.service;

import org.yangxin.pojo.Category;
import org.yangxin.pojo.vo.CategoryVO;

import java.util.List;

/**
 * 分类
 *
 * @author yangxin
 * 2019/11/23 16:17
 */
public interface CategoryService {
    /**
     * 查询所有一级分类
     *
     * @return 一级分类记录集
     */
    List<Category> queryAllRootLevelCategory();

    /**
     * 根据一级分类id查询子分类信息
     *
     * @param rootCategoryId 一级分类id
     * @return 子分类信息
     */
    List<CategoryVO> querySubCategoryList(Integer rootCategoryId);
}
