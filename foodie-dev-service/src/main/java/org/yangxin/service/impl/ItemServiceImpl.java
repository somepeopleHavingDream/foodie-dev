package org.yangxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.CommentLevelEnum;
import org.yangxin.mapper.*;
import org.yangxin.pojo.*;
import org.yangxin.pojo.vo.CommentLevelCountVO;
import org.yangxin.service.ItemService;

import java.util.List;

/**
 * 商品
 *
 * @author yangxin
 * 2019/11/27 21:58
 */
@Service
public class ItemServiceImpl implements ItemService {
    private final ItemsMapper itemsMapper;
    private final ItemsImgMapper itemsImgMapper;
    private final ItemsSpecMapper itemsSpecMapper;
    private final ItemsParamMapper itemsParamMapper;
    private final ItemsCommentsMapper itemsCommentsMapper;

    @Autowired
    public ItemServiceImpl(ItemsMapper itemsMapper, ItemsImgMapper itemsImgMapper, ItemsSpecMapper itemsSpecMapper, ItemsParamMapper itemsParamMapper, ItemsCommentsMapper itemsCommentsMapper) {
        this.itemsMapper = itemsMapper;
        this.itemsImgMapper = itemsImgMapper;
        this.itemsSpecMapper = itemsSpecMapper;
        this.itemsParamMapper = itemsParamMapper;
        this.itemsCommentsMapper = itemsCommentsMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Items queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemsImg> queryItemImageList(String itemId) {
        return itemsImgMapper.selectByItemId(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemsSpec> queryItemSpecList(String itemId) {
        return itemsSpecMapper.selectByItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemsParam queryItemParam(String itemId) {
        return itemsParamMapper.selectByItemId(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public CommentLevelCountVO queryCommentCount(String itemId) {
        int goodCount = selectCommentCount(itemId, CommentLevelEnum.GOOD.getType());
        int normalCount = selectCommentCount(itemId, CommentLevelEnum.NORMAL.getType());
        int badCount = selectCommentCount(itemId, CommentLevelEnum.BAD.getType());

        return CommentLevelCountVO.builder()
                .totalCounts(goodCount + normalCount + badCount)
                .goodCounts(goodCount)
                .normalCounts(normalCount)
                .badCounts(badCount)
                .build();
    }

    /**
     * 查询评价数量
     *
     * @param itemId 商品Id
     * @param level 评价等级
     * @return 评价数
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    int selectCommentCount(String itemId, int level) {
        return itemsCommentsMapper.countByItemIdLevel(itemId, level);
    }
}
