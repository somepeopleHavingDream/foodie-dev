package org.yangxin.mapper;

import org.yangxin.pojo.ItemsComments;
import org.yangxin.pojo.vo.ItemCommentVO;

import java.util.List;
import java.util.Map;

public interface ItemsCommentsMapper {
    int deleteByPrimaryKey(String id);

    int insert(ItemsComments record);

    int insertSelective(ItemsComments record);

    ItemsComments selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(ItemsComments record);

    int updateByPrimaryKey(ItemsComments record);

    /**
     * 查询评价数量
     *
     * @param itemId 商品Id
     * @param level 评价等级
     * @return 评价数
     */
    int countByItemIdLevel(String itemId, int level);

    /**
     * 查询商品评价
     */
    List<ItemCommentVO> selectItemComment(Map<String, Object> map);
}