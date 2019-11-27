package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.pojo.Items;
import org.yangxin.pojo.ItemsImg;
import org.yangxin.pojo.ItemsParam;
import org.yangxin.pojo.ItemsSpec;
import org.yangxin.pojo.vo.ItemInfoVO;
import org.yangxin.service.ItemService;
import org.yangxin.utils.JSONResult;

import java.util.List;

/**
 * 商品
 *
 * @author yangxin
 * 2019/11/27 22:16
 */
@Api(value = "商品接口", tags = {"商品信息展示的相关接口"})
@RestController
@RequestMapping("items")
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
    public JSONResult info(
            @ApiParam(name = "itemId", value = "商品Id", required = true)
            @PathVariable String itemId) {
        log.info("itemId: [{}]", itemId);

        if (StringUtils.isEmpty(itemId)) {
            return JSONResult.errorMsg(null);
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

        return JSONResult.ok(itemInfoVO);
    }
}
