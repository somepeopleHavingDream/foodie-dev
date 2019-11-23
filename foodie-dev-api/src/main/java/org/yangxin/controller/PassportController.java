package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yangxin.enums.ResultEnum;
import org.yangxin.pojo.Users;
import org.yangxin.pojo.query.UserQuery;
import org.yangxin.pojo.vo.UserVO;
import org.yangxin.service.UserService;
import org.yangxin.utils.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

/**
 * 通行证Controller
 *
 * @author yangxin
 * 2019/11/13 21:38
 */
@Api(value = "注册登录", tags = {"用于注册登录的相关接口"})
@RestController
@RequestMapping("/passport")
@Slf4j
public class PassportController {
    private final UserService userService;

    @Autowired
    public PassportController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 用户名是否存在
     *
     * @param username 用户名
     * @return 状态码
     */
    @ApiOperation(value = "用户名是否存在", notes = "用户名是否存在", httpMethod = "GET")
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        log.info("username: [{}]", username);

        // 判断用户名不能为空
        if (StringUtils.isEmpty(username)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_OR_PASSWORD_CANT_EMPTY.getMessage());
        }

        // 查找注册的用户名是否存在
        if (userService.queryUsernameIsExist(username)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_ALREADY_EXIST.getMessage());
        }

        // 请求成功，用户名没有重复
        return JSONResult.ok();
    }

    /**
     * 用户注册
     */
    @ApiOperation(value = "用户注册", notes = "用户注册", httpMethod = "POST")
    // 前端源码里，访问的url地址是register
    @PostMapping("/register")
    public JSONResult register(@RequestBody UserQuery userQuery,
                               HttpServletRequest request,
                               HttpServletResponse response) {
        log.info("userQuery: [{}]", userQuery);

        String username = userQuery.getUsername();
        String password = userQuery.getPassword();
        String confirmPassword = userQuery.getConfirmPassword();

        // 判断用户名和密码必须不为空
        if (StringUtils.isEmpty(username)
                || StringUtils.isEmpty(password)
                || StringUtils.isEmpty(confirmPassword)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_OR_PASSWORD_CANT_EMPTY.getMessage());
        }

        // 查询用户名是否存在
        if (userService.queryUsernameIsExist(username)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_ALREADY_EXIST.getMessage());
        }

        // 密码长度不能少于6位
        if (password.length() < 6) {
            return JSONResult.errorMsg(ResultEnum.PASSWORD_LENGTH_INVALID.getMessage());
        }

        // 判断两次密码是否一致
        if (!Objects.equals(password, confirmPassword)) {
            return JSONResult.errorMsg(ResultEnum.PASSWORD_CONFIRM_NOT_EQUAL.getMessage());
        }

        // 实现注册
        Users users = userService.createUser(userQuery);

        // 封装用户视图对象
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(users, userVO);

        // 设置cookie，cookie值必须被编码，因为cookie值很有可能有违法字符
        CookieUtil.setCookie(request, response, "user", GsonUtil.obj2String(userVO), true);

        return JSONResult.ok();
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserQuery userQuery,
                            HttpServletRequest request,
                            HttpServletResponse response) throws NoSuchAlgorithmException {
        log.info("userQuery: [{}]", userQuery);

        String username = userQuery.getUsername();
        String password = userQuery.getPassword();

        // 判断用户名和密码必须不为空
        if (StringUtils.isEmpty(userQuery) || StringUtils.isEmpty(password)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_OR_PASSWORD_CANT_EMPTY.getMessage());
        }

        // 实现登录
        Users users = userService.queryUserForLogin(username, MD5Util.getMD5Str(password));
        if (users == null) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }

        // 封装用户视图对象
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(users, userVO);

        // 设置cookie，cookie值必须被编码，因为cookie值很有可能有违法字符
        CookieUtil.setCookie(request, response, "user", GsonUtil.obj2String(userVO), true);

        // 响应
        return JSONResult.ok(userVO);
    }

    @ApiOperation(value = "用户退出登录", notes = "用户退出登录", httpMethod = "POST")
    @PostMapping("/logout")
    public JSONResult logout(@RequestParam String userId, HttpServletRequest request, HttpServletResponse response) {
        log.info("userId: [{}]", userId);

        // 清除用户的相关信息的cookie
        CookieUtil.deleteCookie(request, response, "user");

        // todo 用户退出登录，需要清空购物车
        // todo 分布式会话中需要清除用户数据

        return JSONResult.ok();
    }
}
