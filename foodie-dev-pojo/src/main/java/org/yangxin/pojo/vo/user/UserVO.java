package org.yangxin.pojo.vo.user;

import lombok.Data;

/**
 * 用户视图类
 *
 * @author yangxin
 * 2019/11/21 20:46
 */
@Data
public class UserVO {

    /**
     * Id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 头像
     */
    private String face;

    /**
     * 性别
     */
    private Integer sex;
}
