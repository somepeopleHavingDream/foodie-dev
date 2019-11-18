package org.yangxin.pojo.bo;

import lombok.Data;

/**
 * @author yangxin
 * 2019/11/18 20:51
 */
@Data
public class UserBO {
    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 第二次输入的密码
     */
    private String confirmPassword;
}
