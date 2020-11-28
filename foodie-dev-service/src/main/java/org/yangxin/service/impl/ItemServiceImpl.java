package org.yangxin.service.impl;

import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.CommentLevelEnum;
import org.yangxin.enums.ResultEnum;
import org.yangxin.exception.FoodieException;
import org.yangxin.mapper.*;
import org.yangxin.pojo.Item;
import org.yangxin.pojo.ItemImg;
import org.yangxin.pojo.ItemParam;
import org.yangxin.pojo.ItemSpec;
import org.yangxin.pojo.vo.comment.CommentLevelCountVO;
import org.yangxin.pojo.vo.comment.ItemCommentVO;
import org.yangxin.pojo.vo.common.PagingGridVO;
import org.yangxin.pojo.vo.shopcart.ShopCartVO;
import org.yangxin.service.ItemService;
import org.yangxin.service.converter.PageInfo2PagingGridResultConverter;
import org.yangxin.utils.DesensitizationUtil;

import java.util.*;

/**
 * 商品
 *
 * @author yangxin
 * 2019/11/27 21:58
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
@Slf4j
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
    public Item queryItemById(String itemId) {
        return itemsMapper.selectByPrimaryKey(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemImg> queryItemImageList(String itemId) {
        return itemsImgMapper.selectByItemId(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ItemSpec> queryItemSpecList(String itemId) {
        return itemsSpecMapper.selectByItemId(itemId);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public ItemParam queryItemParam(String itemId) {
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

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagingGridVO queryPagingComment(String itemId, Integer level, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("itemId", itemId);
        map.put("level", level);

        // page：第几页；pageSize：每页显示条数
        PageHelper.startPage(page, pageSize);
        List<ItemCommentVO> itemCommentVOList = itemsCommentsMapper.selectItemComment(map);

        // 昵称脱敏
        for (ItemCommentVO itemCommentVO : itemCommentVOList) {
            itemCommentVO.setNickname(DesensitizationUtil.commonDisplay(itemCommentVO.getNickname()));
        }

        return PageInfo2PagingGridResultConverter.convert(itemCommentVOList, page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagingGridVO queryItem(String keyword, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        return PageInfo2PagingGridResultConverter.convert(itemsMapper.selectItem(map), page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public PagingGridVO queryItem(Integer categoryId, String sort, Integer page, Integer pageSize) {
        Map<String, Object> map = new HashMap<>();
        map.put("categoryId", categoryId);
        map.put("sort", sort);

        PageHelper.startPage(page, pageSize);
        return PageInfo2PagingGridResultConverter.convert(itemsMapper.selectItemByThirdCategory(map), page);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<ShopCartVO> queryItemsBySpecIds(String specIds) {
        String[] idArr = specIds.split(",");
        List<String> specIdList = Lists.newArrayList(idArr);

        return itemsMapper.selectItemBySpecIdList(specIdList);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ItemSpec queryItemSpecById(String specId) {
        return itemsSpecMapper.selectByPrimaryKey(specId);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public ItemImg queryItemMainImageById(String itemId) {
        return itemsImgMapper.selectByItemIdIsMain(itemId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void decreaseItemSpecStock(String specId, Integer buyCount) {
        // 库存作为一种共享资源，需要控制
        // synchronized 不推荐使用，集群下无用，性能低下
        // 锁数据库：不推荐，导致数据库性能低下
        // 分布式锁zookeeper redis

        // lockUtil.getLock(); -- 加锁
        // lockUtil.unLock(); -- 解锁

        int result = itemsMapper.decreaseItemSpecStock(specId, buyCount);
        if (result != 1) {
            throw new FoodieException(ResultEnum.DECREASE_STOCK_FAIL);
        }
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
