package org.yangxin.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
}
