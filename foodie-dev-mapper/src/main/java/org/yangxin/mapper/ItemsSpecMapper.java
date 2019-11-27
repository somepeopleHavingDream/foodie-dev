package org.yangxin.mapper;

import org.yangxin.pojo.ItemsSpec;

import java.util.List;

/**
 * 商品规格
 *
 * @author yangxin
 * 2019/11/27 22:09
 */
public interface ItemsSpecMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemsSpec record);

    int insertSelective(ItemsSpec record);

    ItemsSpec selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemsSpec record);

    int updateByPrimaryKey(ItemsSpec record);

    /**
     * 根据商品Id查询商品规格
     *
     * @param itemId 商品Id
     * @return 商品规格
     */
    List<ItemsSpec> selectByItemId(String itemId);
}