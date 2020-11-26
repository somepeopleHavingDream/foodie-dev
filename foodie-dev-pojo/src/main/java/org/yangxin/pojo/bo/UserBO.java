package org.yangxin.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yangxin
 * 2019/11/18 20:51
 */
@ApiModel(value = "用户业务对象", description = "从客户端由用户传入的数据封装在此entity中")
@Data
public class UserBO {

    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名", name = "username", example = "imooc", required = true)
    private String username;

    /**
     * 密码
     */
    @ApiModelProperty(value = "密码", name = "password", example = "123456", required = true)
    private String password;

    /**
     * 第二次输入的密码
     */
    @ApiModelProperty(value = "确认密码", name = "confirmPassword", example = "123456")
    private String confirmPassword;
}
