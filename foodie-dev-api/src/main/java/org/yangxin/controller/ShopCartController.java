package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yangxin.pojo.query.ShopCartQuery;
import org.yangxin.pojo.vo.common.JSONVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 购物车
 *
 * @author yangxin
 * 2019/12/04 10:29
 */
@Api(value = "购物车接口Controller", tags = {"购物车接口相关的API"})
@RequestMapping("/shopcart")
@RestController
@Slf4j
public class ShopCartController {
    @ApiOperation(value = "添加商品到购物车", notes = "添加商品到购物车", httpMethod = "POST")
    @PostMapping("/add")
    public JSONVO add(@RequestParam String userId,
                      @RequestBody ShopCartQuery shopCartQuery,
                      HttpServletRequest httpServletRequest,
                      HttpServletResponse httpServletResponse) {
        if (StringUtils.isEmpty(userId)) {
            return JSONVO.errorMap("");
        }

        log.info("shopCartQuery: [{}]", shopCartQuery);

        // todo 前端用户在登录的情况下，添加商品到购物车，会同时在后端同步购物车到redis缓存

        return JSONVO.ok();
    }
}
