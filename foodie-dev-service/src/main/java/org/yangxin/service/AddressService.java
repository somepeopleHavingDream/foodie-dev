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
     * 根据用户Id和地址Id，查询具体的用户地址对象信息
     *
     * @param userId 用户Id
     * @param addressId 地址Id
     * @return 用户地址对象信息
     */
    UserAddress queryUserAddress(String userId, String addressId);

    /**
     * 用户新增地址
     */
    void addNewUserAddress(AddressQuery addressQuery);

    /**
     * 用户修改地址
     */
    void updateUserAddress(AddressQuery addressQuery);

    /**
     * 修改默认地址
     *
     * @param userId 用户Id
     * @param addressId 地址Id
     */
    void updateUserAddressAsDefault(String userId, String addressId);

    /**
     * 根据用户Id和地址Id，删除对应的用户地址信息
     *
     * @param addressId 地址Id
     */
    void deleteUserAddress(String addressId);
}
