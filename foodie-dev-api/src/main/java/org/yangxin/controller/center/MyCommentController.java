package org.yangxin.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.yangxin.controller.BaseController;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.pojo.OrderItems;
import org.yangxin.pojo.Order;
import org.yangxin.pojo.bo.center.OrderItemsCommentBO;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.service.center.MyCommentService;
import org.yangxin.utils.PagedGridResult;

import java.util.List;
import java.util.Objects;

/**
 * @author yangxin
 * 2020/11/27 13:23
 */
@Api(value = "用户中心评价模块", tags = {"用户中心评价模块相关接口"})
@RestController
@RequestMapping("mycomments")
@Slf4j
public class MyCommentController extends BaseController {

    private final MyCommentService myCommentService;

    @Autowired
    public MyCommentController(MyCommentService myCommentService) {
        this.myCommentService = myCommentService;
    }

    /**
     * 查询订单列表
     *
     * @param userId 用户Id
     * @param orderId 订单Id
     */
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
        Order myOrder = (Order) checkResult.getData();
        if (Objects.equals(myOrder.getIsComment(), YesNoEnum.YES.getType())) {
            return JSONVO.errorMsg("该笔订单已经评价。");
        }

        List<OrderItems> orderItemsList = myCommentService.queryPendingComment(orderId);
        return JSONVO.ok(orderItemsList);
    }

    /**
     * 保存评论列表
     *
     * @param userId 用户Id
     * @param orderId 订单Id
     */
    @ApiOperation(value = "保存评论列表", notes = "保存评论列表", httpMethod = "POST")
    @PostMapping("/saveList")
    public JSONVO saveList(@ApiParam(name = "userId", value = "用户Id", required = true)
                          @RequestParam String userId,
                          @ApiParam(name = "orderId", value = "订单Id", required = true)
                          @RequestParam String orderId,
                          @RequestBody List<OrderItemsCommentBO> commentList) {
        log.info("commentList: [{}]", commentList);

        // 判断用户和订单是否有关联
        JSONVO checkResult = checkUserOrder(userId, orderId);
        if (checkResult.getStatus() != HttpStatus.OK.value()) {
            return checkResult;
        }

        // 判断评论内容list不能为空
        if (commentList == null || commentList.isEmpty()) {
            return JSONVO.errorMsg("评论内容不能为空！");
        }

        myCommentService.saveComments(orderId, userId, commentList);

        return JSONVO.ok();
    }

    /**
     * 查询我的评价
     */
    @ApiOperation(value = "查询我的评价", notes = "查询我的评价", httpMethod = "POST")
    @GetMapping("/query")
    public JSONVO query(
            @ApiParam(name = "userId", value = "用户Id", required = true)
            @RequestParam String userId,
            @ApiParam(name = "page", value = "查询第几页")
            @RequestParam(defaultValue = "1") Integer page,
            @ApiParam(name = "pageSize", value = "分页的每一页显示的条数")
            @RequestParam(defaultValue = "10") Integer pageSize) {
        log.info("page: [{}], pageSize: [{}]", page, pageSize);

        if (StringUtils.isBlank(userId)) {
            return JSONVO.errorMsg(null);
        }

        PagedGridResult pagedGridResult = myCommentService.queryMyComments(userId, page, pageSize);
        return JSONVO.ok(pagedGridResult);
    }
}
