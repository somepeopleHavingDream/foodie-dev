package org.yangxin.mapper;

import org.apache.ibatis.annotations.Param;
import org.yangxin.pojo.Item;
import org.yangxin.pojo.vo.item.SearchItemsVO;
import org.yangxin.pojo.vo.shopcart.ShopCartVO;

import java.util.List;
import java.util.Map;

public interface ItemsMapper {
    int deleteByPrimaryKey(String id);

    int insert(Item record);

    int insertSelective(Item record);

    Item selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Item record);

    int updateByPrimaryKeyWithBLOBs(Item record);

    int updateByPrimaryKey(Item record);

    /**
     * 减库存
     */
    int decreaseItemSpecStock(String specId, Integer pendingCount);

    /**
     * 查询商品列表
     */
    List<SearchItemsVO> selectItem(@Param("paramsMap") Map<String, Object> map);

    /**
     * 通过三级分类，查询商品列表
     */
    List<SearchItemsVO> selectItemByThirdCategory(@Param("paramsMap") Map<String, Object> map);

    /**
     * 根据规格ids查询最新的购物车中的商品数据（用于刷新渲染购物车中的商品数据）
     */
    List<ShopCartVO> selectItemBySpecIdList(@Param("specIdList")List<String> specIdList);
}