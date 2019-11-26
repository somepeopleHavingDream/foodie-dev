package org.yangxin.mapper;

import org.apache.ibatis.annotations.Param;
import org.yangxin.pojo.Category;
import org.yangxin.pojo.vo.CategoryVO;
import org.yangxin.pojo.vo.NewItemsVO;

import java.util.List;
import java.util.Map;

public interface CategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Category record);

    int insertSelective(Category record);

    Category selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Category record);

    int updateByPrimaryKey(Category record);

    /**
     * 通过类型，查询所有分类记录
     *
     * @param type 类别
     * @return 分类记录
     */
    List<Category> selectByType(Integer type);

    /**
     * 根据一级分类id查询子分类信息
     *
     * @param rootCategoryId 一级分类id
     * @return 子分类信息
     */
    List<CategoryVO> selectByRootCategoryId(Integer rootCategoryId);

    /**
     * 查询首页每个一级分类下的6条最新商品数据
     */
    List<NewItemsVO> querySixNewItemsLazy(@Param("paramsMap") Map<String, Object> map);
}