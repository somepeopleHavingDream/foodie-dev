package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yangxin.pojo.Items;
import org.yangxin.pojo.ItemsImg;
import org.yangxin.pojo.ItemsParam;
import org.yangxin.pojo.ItemsSpec;
import org.yangxin.pojo.vo.ItemInfoVO;
import org.yangxin.pojo.vo.JSONVO;
import org.yangxin.pojo.vo.PagingGridVO;
import org.yangxin.service.ItemService;

import java.util.List;

/**
 * 商品
 *
 * @author yangxin
 * 2019/11/27 22:16
 */
@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("/items")
@Slf4j
public class ItemsController {
    private final ItemService itemService;

    @Autowired
    public ItemsController(ItemService itemService) {
        this.itemService = itemService;
    }

    /**
     * 商品详情
     */
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情", httpMethod = "GET")
    @GetMapping("/info/{itemId}")
    public JSONVO info(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @PathVariable String itemId) {
        log.info("itemId: [{}]", itemId);

        if (StringUtils.isEmpty(itemId)) {
            return JSONVO.errorMsg(null);
        }

        Items items = itemService.queryItemById(itemId);
        List<ItemsImg> itemImageList = itemService.queryItemImageList(itemId);
        List<ItemsSpec> itemsSpecList = itemService.queryItemSpecList(itemId);
        ItemsParam itemsParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = ItemInfoVO.builder()
                .item(items)
                .itemImgList(itemImageList)
                .itemSpecList(itemsSpecList)
                .itemParams(itemsParam)
                .build();

        return JSONVO.ok(itemInfoVO);
    }

    /**
     * 商品评价等级
     */
    @ApiOperation(value = "查询商品评价等级", notes = "查询商品评价等级", httpMethod = "GET")
    @GetMapping("/commentLevel")
    public JSONVO commentLevel(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @RequestParam String itemId) {
        log.info("itemId: [{}]", itemId);

        if (StringUtils.isEmpty(itemId)) {
            return JSONVO.errorMsg(null);
        }

        return JSONVO.ok(itemService.queryCommentCount(itemId));
    }

    /**
     * 商品评论
     */
    @ApiOperation(value = "查询商品评论", notes = "查询商品评论", httpMethod = "GET")
    @GetMapping("/comments")
    public JSONVO comment(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @RequestParam String itemId,
            @ApiParam(name = "level", value = "评价等级")
            @RequestParam Integer level,
            @ApiParam(name = "page", value = "查询第几页")
            @RequestParam(defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数")
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("itemId: [{}], level: [{}]", itemId, level);
        log.info("page: [{}], pageSize: [{}]", page, pageSize);

        if (StringUtils.isEmpty(itemId)) {
            return JSONVO.errorMsg(null);
        }

        PagingGridVO pagingGridVO = itemService.queryPagingComment(itemId, level, page, pageSize);
        return JSONVO.ok(pagingGridVO);
    }
}
