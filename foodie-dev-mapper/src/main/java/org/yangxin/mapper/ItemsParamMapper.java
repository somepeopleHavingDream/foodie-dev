package org.yangxin.mapper;

import org.yangxin.pojo.ItemParam;

public interface ItemsParamMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemParam record);

    int insertSelective(ItemParam record);

    ItemParam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemParam record);

    int updateByPrimaryKey(ItemParam record);

    /**
     * 根据商品Id查询商品参数
     *
     * @param itemId 商品Id
     * @return 商品参数
     */
    ItemParam selectByItemId(String itemId);
}