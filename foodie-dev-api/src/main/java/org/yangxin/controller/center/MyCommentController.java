package org.yangxin.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.controller.BaseController;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.pojo.OrderItems;
import org.yangxin.pojo.Orders;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.service.center.MyCommentService;

import java.util.List;
import java.util.Objects;

/**
 * @author yangxin
 * 2020/11/27 13:23
 */
@Api(value = "用户中心评价模块", tags = {"用户中心评价模块相关接口"})
@RestController
@RequestMapping("mycomments")
public class MyCommentController extends BaseController {

    private final MyCommentService myCommentService;

    @Autowired
    public MyCommentController(MyCommentService myCommentService) {
        this.myCommentService = myCommentService;
    }

    @ApiOperation(value = "查询订单列表", notes = "查询订单列表", httpMethod = "POST")
    @PostMapping("/pending")
    public JSONVO comment(@ApiParam(name = "userId", value = "用户Id", required = true)
                          @RequestParam String userId,
                          @ApiParam(name = "orderId", value = "订单Id", required = true)
                          @RequestParam String orderId) {
        // 判断用户和订单是否有关联
        JSONVO checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 判断该笔订单是否已经评价过，评价过了就不再继续
        Orders myOrder = (Orders) checkResult.getData();
        if (Objects.equals(myOrder.getIsComment(), YesNoEnum.YES.getType())) {
            return JSONVO.errorMsg("该笔订单已经评价。");
        }

        List<OrderItems> orderItemsList = myCommentService.queryPendingComment(orderId);
        return JSONVO.ok(orderItemsList);
    }
}
