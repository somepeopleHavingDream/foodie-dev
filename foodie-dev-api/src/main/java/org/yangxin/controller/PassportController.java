package org.yangxin.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.yangxin.enums.ResultEnum;
import org.yangxin.service.UserService;
import org.yangxin.utils.JSONResult;

/**
 * 通行证Controller
 *
 * @author yangxin
 * 2019/11/13 21:38
 */
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
    @GetMapping("/usernameIsExist")
    public JSONResult usernameIsExist(@RequestParam String username) {
        log.info("username: [{}]", username);

        // 判断用户名不能为空
        if (StringUtils.isEmpty(username)) {
//            return JSONResult.errorMsg("用户名不能为空");
            return JSONResult.errorMsg(ResultEnum.USERNAME_CANT_EMPTY.getMessage());
        }

        // 查找注册的用户名是否存在
        if (userService.queryUsernameIsExist(username)) {
            return JSONResult.errorMsg(ResultEnum.USERNAME_ALREADY_EXIST.getMessage());
        }

        // 请求成功，用户名没有重复
        return JSONResult.ok();
    }
}
