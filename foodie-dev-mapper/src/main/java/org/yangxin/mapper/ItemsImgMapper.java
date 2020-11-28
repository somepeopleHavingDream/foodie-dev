package org.yangxin.mapper;

import org.yangxin.pojo.ItemImg;

import java.util.List;

public interface ItemsImgMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemImg record);

    int insertSelective(ItemImg record);

    ItemImg selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemImg record);

    int updateByPrimaryKey(ItemImg record);

    /**
     * 根据商品Id查询商品图片列表
     *
     * @param itemId 商品Id
     * @return 商品图片
     */
    List<ItemImg> selectByItemId(String itemId);

    /**
     * 根据商品Id，获得商品图片主图url
     *
     * @param itemId 商品Id
     * @return 商品图片主图url
     */
    ItemImg selectByItemIdIsMain(String itemId);
}