package org.yangxin.service;

import org.yangxin.pojo.UserAddress;
import org.yangxin.pojo.query.AddressQuery;

import java.util.List;

/**
 * 地址
 *
 * @author yangxin
 * 2019/12/04 20:46
 */
public interface AddressService {
    /**
     * 根据用户Id，查询用户的收货地址列表
     *
     * @param userId 用户Id
     * @return 收货地址列表
     */
    List<UserAddress> queryAll(String userId);

    /**
     * 用户新增地址
     */
    void addNewUserAddress(AddressQuery addressQuery);
}
