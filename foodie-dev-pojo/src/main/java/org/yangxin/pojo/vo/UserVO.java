package org.yangxin.pojo.vo;

import lombok.Data;

/**
 * 用户视图类
 *
 * @author yangxin
 * 2019/11/21 20:46
 */
@Data
public class UserVO {
    private String id;
    private String username;
    private String nickname;
    private String realname;
    private String face;
    private Integer sex;
}
