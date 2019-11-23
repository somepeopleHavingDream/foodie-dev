package org.yangxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yangxin.enums.CategoryTypeEnum;
import org.yangxin.mapper.CategoryMapper;
import org.yangxin.pojo.Category;
import org.yangxin.service.CategoryService;

import java.util.List;

/**
 * 分类
 *
 * @author yangxin
 * 2019/11/23 16:21
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public CategoryServiceImpl(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> queryAllRootLevelCategory() {
        return categoryMapper.selectByType(CategoryTypeEnum._1_CATEGORY.getType());
    }
}
