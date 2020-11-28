package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yangxin.pojo.Item;
import org.yangxin.pojo.ItemImg;
import org.yangxin.pojo.ItemParam;
import org.yangxin.pojo.ItemSpec;
import org.yangxin.pojo.vo.item.ItemInfoVO;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.pojo.vo.common.PagingGridVO;
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

        Item item = itemService.queryItemById(itemId);
        List<ItemImg> itemImageList = itemService.queryItemImageList(itemId);
        List<ItemSpec> itemSpecList = itemService.queryItemSpecList(itemId);
        ItemParam itemParam = itemService.queryItemParam(itemId);

        ItemInfoVO itemInfoVO = ItemInfoVO.builder()
                .item(item)
                .itemImgList(itemImageList)
                .itemSpecList(itemSpecList)
                .itemParams(itemParam)
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

    /**
     * 商品列表
     */
    @ApiOperation(value = "查询商品列表", notes = "查询商品列表", httpMethod = "GET")
    @GetMapping("/search")
    public JSONVO search(
            @ApiParam(name = "keywords", value = "关键字", required = true)
            @RequestParam(name = "keywords") String keyword,
            @ApiParam(name = "sort", value = "排序")
            @RequestParam String sort,
            @ApiParam(name = "page", value = "查询第几页")
            @RequestParam(defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数")
            @RequestParam(defaultValue = "20") Integer pageSize) {
        log.info("keyword: [{}], sort: [{}]", keyword, sort);
        log.info("page: [{}], pageSize: [{}]", page, pageSize);

        if (StringUtils.isEmpty(keyword)) {
            return JSONVO.errorMsg(null);
        }

        return JSONVO.ok(itemService.queryItem(keyword, sort, page, pageSize));
    }

    /**
     * 分类的商品列表
     */
    @ApiOperation(value = "通过分类Id搜索商品列表", notes = "通过分类Id搜索商品列表", httpMethod = "GET")
    @GetMapping("/catItems")
    public JSONVO categoryItem(
            @ApiParam(name = "catId", value = "三级分类Id", required = true)
            @RequestParam(name = "catId") Integer categoryId,
            @ApiParam(name = "sort", value = "排序")
            @RequestParam String sort,
            @ApiParam(name = "page", value = "查询第几页")
            @RequestParam(defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数")
            @RequestParam(defaultValue = "20") Integer pageSize) {
        log.info("categoryId: [{}], sort: [{}]", categoryId, sort);
        log.info("page: [{}], pageSize: [{}]", page, pageSize);

        return JSONVO.ok(itemService.queryItem(categoryId, sort, page, pageSize));
    }

    /**
     * 刷新购物车
     */
    @ApiOperation(value = "根据商品规格Ids查找最新的商品数据", notes = "根据商品规格Ids查找最新的商品数据", httpMethod = "GET")
    @GetMapping("/refresh")
    public JSONVO refresh(
            @ApiParam(name = "itemSpecIds", value = "拼接的规格ids", required = true, example = "1001,1003,1005")
            @RequestParam String itemSpecIds) {
        log.info("itemSpecIds: [{}]", itemSpecIds);

        if (StringUtils.isEmpty(itemSpecIds)) {
            return JSONVO.ok();
        }

        return JSONVO.ok(itemService.queryItemsBySpecIds(itemSpecIds));
    }
}
