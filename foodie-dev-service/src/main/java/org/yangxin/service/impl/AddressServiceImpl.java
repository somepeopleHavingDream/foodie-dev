package org.yangxin.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.mapper.UserAddressMapper;
import org.yangxin.pojo.UserAddress;
import org.yangxin.service.AddressService;

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

    @Autowired
    public AddressServiceImpl(UserAddressMapper userAddressMapper) {
        this.userAddressMapper = userAddressMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<UserAddress> queryAll(String userId) {
        return userAddressMapper.selectByUserId(userId);
    }
}
