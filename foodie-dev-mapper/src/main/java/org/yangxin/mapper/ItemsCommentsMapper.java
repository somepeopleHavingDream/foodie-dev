package org.yangxin.mapper;

import org.apache.ibatis.annotations.Param;
import org.yangxin.pojo.ItemsComments;
import org.yangxin.pojo.vo.comment.ItemCommentVO;
import org.yangxin.pojo.vo.comment.MyCommentVO;

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

    /**
     * 保存评论
     */
    void saveComments(Map<String, Object> map);

    /**
     * 查出我的评论
     */
    List<MyCommentVO> queryMyComments(@Param("paramMap") Map<String, Object> map);
}