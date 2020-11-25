package org.yangxin.service;

import org.yangxin.pojo.User;
import org.yangxin.pojo.query.UserQuery;

/**
 * 用户
 *
 * @author yangxin
 * 2019/11/13 21:31
 */
public interface UserService {
    /**
     * 判断用户名是否存在
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean queryUsernameIsExist(String username);

    /**
     * 创建用户
     */
    User createUser(UserQuery userQuery);

    /**
     * 检索用户名和密码是否匹配，用于登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    User queryUserForLogin(String username, String password);
}
