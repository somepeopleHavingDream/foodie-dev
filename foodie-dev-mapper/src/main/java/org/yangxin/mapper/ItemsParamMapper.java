package org.yangxin.mapper;

import org.yangxin.pojo.ItemsParam;

public interface ItemsParamMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemsParam record);

    int insertSelective(ItemsParam record);

    ItemsParam selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemsParam record);

    int updateByPrimaryKey(ItemsParam record);

    /**
     * 根据商品Id查询商品参数
     *
     * @param itemId 商品Id
     * @return 商品参数
     */
    ItemsParam selectByItemId(String itemId);
}