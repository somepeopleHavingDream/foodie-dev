package org.yangxin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.yangxin.enums.ResultEnum;
import org.yangxin.pojo.Users;
import org.yangxin.pojo.bo.UserBO;
import org.yangxin.service.UserService;
import org.yangxin.utils.JSONResult;
import org.yangxin.utils.MD5Util;

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
    public JSONResult register(@RequestBody UserBO userBO) {
        log.info("userBO: [{}]", userBO);

        String username = userBO.getUsername();
        String password = userBO.getPassword();
        String confirmPassword = userBO.getConfirmPassword();

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
        userService.createUser(userBO);

        return JSONResult.ok();
    }

    /**
     * 用户登录
     */
    @ApiOperation(value = "用户登录", notes = "用户登录", httpMethod = "POST")
    @PostMapping("/login")
    public JSONResult login(@RequestBody UserBO userBO) throws NoSuchAlgorithmException {
        log.info("userBO: [{}]", userBO);

        String username = userBO.getUsername();
        String password = userBO.getPassword();

        // 判断用户名和密码必须不为空
        if (StringUtils.isEmpty(userBO) || StringUtils.isEmpty(password)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_OR_PASSWORD_CANT_EMPTY.getMessage());
        }

        // 实现登录
        Users users = userService.queryUserForLogin(username, MD5Util.getMD5Str(password));
        if (users == null) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_OR_PASSWORD_ERROR.getMessage());
        }

        return JSONResult.ok(users);
    }
}
