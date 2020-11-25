package org.yangxin.pojo.query;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.yangxin.pojo.annotation.Phone;

import javax.validation.constraints.NotBlank;

/**
 * 地址
 *
 * @author yangxin
 * 2019/12/05 10:31
 */
@Data
public class AddressBO {

    /**
     * 地址Id
     */
    private String addressId;

    /**
     * 用户Id
     */
    private String userId;

    /**
     * 收件人
     */
    @NotBlank(message = "收件人不能为空")
    @Length(max = 12, message = "收货人姓名不能太长")
    private String receiver;

    /**
     * 手机号码
     */
    @NotBlank(message = "收货人手机号不能为空")
    @Length(min = 11, max = 11, message = "收货人手机号长度不正确")
    @Phone
    private String mobile;

    /**
     * 省份
     */
    @NotBlank(message = "收货地址信息不能为空")
    private String province;

    /**
     * 城市
     */
    @NotBlank(message = "收货地址信息不能为空")
    private String city;

    /**
     * 区
     */
    @NotBlank(message = "收货地址信息不能为空")
    private String district;

    /**
     * 细节
     */
    @NotBlank(message = "收货地址信息不能为空")
    private String detail;
}
