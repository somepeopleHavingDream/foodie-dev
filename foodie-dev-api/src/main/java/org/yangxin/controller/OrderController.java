package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.enums.OrderStatusEnum;
import org.yangxin.enums.PayMethodEnum;
import org.yangxin.enums.ResultEnum;
import org.yangxin.pojo.query.SubmitOrderQuery;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.pojo.vo.order.MerchantOrdersVO;
import org.yangxin.pojo.vo.order.OrderVO;
import org.yangxin.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

import static org.yangxin.controller.BaseController.payReturnUrl;

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
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @ApiOperation(value = "用户下单", notes = "用户下单", httpMethod = "POST")
    @PostMapping("/create")
    public JSONVO create(@RequestBody SubmitOrderQuery submitOrderQuery,
                         HttpServletRequest httpServletRequest,
                         HttpServletResponse httpServletResponse) {
        log.info("submitOrderQuery: [{}]", submitOrderQuery);

        // 判断支付方式
        Integer payMethod = submitOrderQuery.getPayMethod();
        if (Objects.equals(payMethod, PayMethodEnum.WEIXIN.getType())
                && Objects.equals(payMethod, PayMethodEnum.ALIPAY.getType())) {
            return JSONVO.errorMsg(ResultEnum.PAY_METHOD_NOT_SUPPORTED.getMessage());
        }

        // 创建订单
        OrderVO orderVO = orderService.createOrder(submitOrderQuery);
        String orderId = orderVO.getOrderId();
        MerchantOrdersVO merchantOrdersVO = orderVO.getMerchantOrdersVO();
        merchantOrdersVO.setReturnUrl(payReturnUrl);

        // 创建订单以后，移除购物车中已结算（已提交）的商品
        /*
          1001
          2002 -> 用户购买
          3003 -> 用户购买
          4004
         */
        // todo 整合redis之后，完善购物车中的已结算商品清除，并且同步到前端的cookie
//        CookieUtil.setCookie(httpServletRequest, httpServletResponse, FOODIE_SHOP_CART, "", true);

        // 向支付中心发送当前订单，用于保存支付中心的订单数据

        return JSONVO.ok(orderId);
    }

    /**
     * 通知订单支付（未支付）
     */
    @PostMapping("/notifyMerchantOrderPaid")
    public Integer notifyMerchantOrderPaid(String merchantOrderId) {
        log.info("merchantOrderId: [{}]", merchantOrderId);

        orderService.updateOrderStatus(merchantOrderId, OrderStatusEnum.WAIT_PAY.getType());
        return HttpStatus.OK.value();
    }
}