package org.yangxin.controller.center;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.yangxin.pojo.User;
import org.yangxin.pojo.query.center.CenterUserBO;
import org.yangxin.pojo.vo.common.JSONVO;
import org.yangxin.pojo.vo.user.UserVO;
import org.yangxin.service.center.CenterUserService;
import org.yangxin.utils.CookieUtil;
import org.yangxin.utils.GSONUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yangxin
 * 2020/11/25 15:07
 */
@Api(value = "用户信息接口", tags = {"用户信息相关接口"})
@RestController
@RequestMapping("userInfo")
public class CenterUserController {

    private final CenterUserService centerUserService;

    @Autowired
    public CenterUserController(CenterUserService centerUserService) {
        this.centerUserService = centerUserService;
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息", httpMethod = "GET")
    @GetMapping("/update")
    public JSONVO update(@ApiParam(name = "userId", value = "用户id", required = true)
                           @RequestParam String userId,
                         @RequestBody CenterUserBO centerUserBO,
                         HttpServletRequest request,
                         HttpServletResponse response) {
        User user = centerUserService.updateUserInfo(userId, centerUserBO);

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user, userVO);

        CookieUtil.setCookie(request, response, "user", GSONUtil.obj2String(userVO), true);

        // todo 后续要改，增加令牌token，会整合进redis，分布式会话

        return JSONVO.ok();
    }
}
