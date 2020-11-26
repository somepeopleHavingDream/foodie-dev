package org.yangxin.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.service.center.MyOrderService;
import org.yangxin.utils.PagedGridResult;

/**
 * @author yangxin
 * 2020/11/26 21:06
 */
@Api(value = "用户中心我的订单", tags = {"用户中心我的订单相关接口"})
@RestController
@RequestMapping("myorders")
public class MyOrderController {

    private final MyOrderService myOrderService;

    @Autowired
    public MyOrderController(MyOrderService myOrderService) {
        this.myOrderService = myOrderService;
    }

    /**
     * 查询我的订单列表
     */
    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/query")
    public JSONVO query(@ApiParam(name = "userId", value = "用户Id", required = true)
                        @RequestParam String userId,
                        @ApiParam(name = "orderStatus", value = "订单状态")
                        @RequestParam Integer orderStatus,
                        @ApiParam(name = "page", value = "查询下一页的第几页")
                        @RequestParam(defaultValue = "1") Integer page,
                        @ApiParam(name = "pageSize", value = "分页的每一页显示的条数")
                        @RequestParam(defaultValue = "10") Integer pageSize) {
        if (StringUtils.isBlank(userId)) {
            return JSONVO.errorMsg(null);
        }

        return JSONVO.ok(myOrderService.queryMyOrders(userId, orderStatus, page, pageSize));
    }

    /**
     * 商家发货没有后端，所以这个接口仅仅只是用于模拟
     */
    @ApiOperation(value = "商家发货", notes = "商家发货", httpMethod = "GET")
    @GetMapping("/deliver")
    public JSONVO deliver(@ApiParam(name = "orderId", value = "订单Id", required = true)
                          @RequestParam String orderId) {
        if (StringUtils.isBlank(orderId)) {
            return JSONVO.errorMsg("订单Id不能为空");
        }

        myOrderService.updateDeliverOrderStatus(orderId);
        return JSONVO.ok();
    }

    /**
     * 用户确认收货
     */
    @ApiOperation(value = "用户确认收货", notes = "用户确认收货", httpMethod = "POST")
    @PostMapping("/confirmReceive")
    public JSONVO confirmReceive(@ApiParam(name = "orderId", value = "订单Id", required = true)
                                 @RequestParam String orderId,
                                 @ApiParam(name = "userId", value = "用户Id", required = true)
                                 @RequestParam String userId) {
        JSONVO checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        return JSONVO.ok();
    }

    /**
     * 用户删除订单
     */
    @ApiOperation(value = "用户删除订单", notes = "用户删除订单", httpMethod = "POST")
    @PostMapping("/delete")
    public JSONVO delete(@ApiParam(name = "orderId", value = "订单Id", required = true)
                                 @RequestParam String orderId,
                                 @ApiParam(name = "userId", value = "用户Id", required = true)
                                 @RequestParam String userId) {
        JSONVO checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        return JSONVO.ok();
    }

    /**
     * 用于验证用户和订单是否有关联关系，避免非法用户调用
     *
     * @param userId 用户Id
     * @param orderId 订单Id
     */
    private JSONVO checkUserOrder(String userId, String orderId) {
        if (myOrderService.queryMyOrder(userId, orderId) == null) {
            return JSONVO.errorMsg("订单不存在！");
        }
        return JSONVO.ok();
    }
}
