package org.yangxin.service;

import org.yangxin.pojo.Category;

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
}
