package org.yangxin.service;

import org.yangxin.pojo.Items;
import org.yangxin.pojo.ItemsImg;
import org.yangxin.pojo.ItemsParam;
import org.yangxin.pojo.ItemsSpec;
import org.yangxin.pojo.vo.comment.CommentLevelCountVO;
import org.yangxin.pojo.vo.common.PagingGridVO;

import java.util.List;

/**
 * 商品
 *
 * @author yangxin
 * 2019/11/27 21:53
 */
public interface ItemService {
    /**
     * 根据商品Id查询详情
     *
     * @param itemId 商品Id
     * @return 商品
     */
    Items queryItemById(String itemId);

    /**
     * 根据商品Id查询商品图片列表
     *
     * @param itemId 商品Id
     * @return 商品图片
     */
    List<ItemsImg> queryItemImageList(String itemId);

    /**
     * 根据商品Id查询商品规格
     *
     * @param itemId 商品Id
     * @return 商品规格
     */
    List<ItemsSpec> queryItemSpecList(String itemId);

    /**
     * 根据商品Id查询商品参数
     *
     * @param itemId 商品Id
     * @return 商品参数
     */
    ItemsParam queryItemParam(String itemId);

    /**
     * 根据商品Id查询商品评价
     *
     * @param itemId 商品Id
     * @return 商品评价
     */
    CommentLevelCountVO queryCommentCount(String itemId);

    /**
     * 根据商品Id查询商品的评价（分页）
     */
    PagingGridVO queryPagingComment(String itemId, Integer level, Integer page, Integer pageSize);
}
