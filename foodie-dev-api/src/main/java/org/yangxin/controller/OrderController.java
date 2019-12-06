package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.enums.PayMethodEnum;
import org.yangxin.enums.ResultEnum;
import org.yangxin.pojo.query.SubmitOrderQuery;
import org.yangxin.pojo.vo.common.JSONVO;

import java.util.Objects;

/**
 * 订单
 *
 * @author yangxin
 * 2019/12/06 10:23
 */
@Api(value = "订单相关", tags = {"订单相关的api接口"})
@RequestMapping("/orders")
@RestController
@Slf4j
public class OrderController {
    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JSONVO create(@RequestBody SubmitOrderQuery submitOrderQuery) {
        log.info("submitOrderQuery: [{}]", submitOrderQuery);

        // 判断支付方式
        Integer payMethod = submitOrderQuery.getPayMethod();
        if (Objects.equals(payMethod, PayMethodEnum.WEIXIN.getType())
                && Objects.equals(payMethod, PayMethodEnum.ALIPAY.getType())) {
            return JSONVO.errorMsg(ResultEnum.PAY_METHOD_NOT_SUPPORTED.getMessage());
        }

        // 创建订单
        // 创建订单以后，移除购物车中已结算（已提交）的商品
        // 向支付中心发送当前订单，用于保存支付中心的订单数据

        return JSONVO.ok();
    }
}
