package org.yangxin.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yangxin.mapper.UserAddressMapper;
import org.yangxin.pojo.UserAddress;
import org.yangxin.pojo.query.AddressQuery;
import org.yangxin.service.AddressService;

import java.util.Date;
import java.util.List;

/**
 * 地址
 *
 * @author yangxin
 * 2019/12/04 20:54
 */
@Service
public class AddressServiceImpl implements AddressService {
    private final UserAddressMapper userAddressMapper;
    private final Sid sid;

    @Autowired
    public AddressServiceImpl(UserAddressMapper userAddressMapper, Sid sid) {
        this.userAddressMapper = userAddressMapper;
        this.sid = sid;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserAddress> queryAll(String userId) {
        return userAddressMapper.selectByUserId(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewUserAddress(AddressQuery addressQuery) {
        // 判断当前用户是否存在地址，如果没有，则新增为“默认地址”
        int isDefault = 0;
        List<UserAddress> userAddressList = queryAll(addressQuery.getUserId());
        if (CollectionUtils.isEmpty(userAddressList)) {
            isDefault = 1;
        }

        // 保存地址到数据库
        UserAddress userAddress = new UserAddress();
        BeanUtils.copyProperties(addressQuery, userAddress);

        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }
}
