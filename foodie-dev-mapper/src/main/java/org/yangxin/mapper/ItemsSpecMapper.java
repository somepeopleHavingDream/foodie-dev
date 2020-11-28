package org.yangxin.mapper;

import org.yangxin.pojo.ItemSpec;

import java.util.List;

/**
 * 商品规格
 *
 * @author yangxin
 * 2019/11/27 22:09
 */
public interface ItemsSpecMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemSpec record);

    int insertSelective(ItemSpec record);

    ItemSpec selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemSpec record);

    int updateByPrimaryKey(ItemSpec record);

    /**
     * 根据商品Id查询商品规格
     *
     * @param itemId 商品Id
     * @return 商品规格
     */
    List<ItemSpec> selectByItemId(String itemId);
}