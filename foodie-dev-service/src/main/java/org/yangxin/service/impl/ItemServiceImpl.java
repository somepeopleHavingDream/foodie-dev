package org.yangxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.mapper.ItemsImgMapper;
import org.yangxin.mapper.ItemsMapper;
import org.yangxin.mapper.ItemsParamMapper;
import org.yangxin.mapper.ItemsSpecMapper;
import org.yangxin.pojo.Items;
import org.yangxin.pojo.ItemsImg;
import org.yangxin.pojo.ItemsParam;
import org.yangxin.pojo.ItemsSpec;
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

    @Autowired
    public ItemServiceImpl(ItemsMapper itemsMapper, ItemsImgMapper itemsImgMapper, ItemsSpecMapper itemsSpecMapper, ItemsParamMapper itemsParamMapper) {
        this.itemsMapper = itemsMapper;
        this.itemsImgMapper = itemsImgMapper;
        this.itemsSpecMapper = itemsSpecMapper;
        this.itemsParamMapper = itemsParamMapper;
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
}
