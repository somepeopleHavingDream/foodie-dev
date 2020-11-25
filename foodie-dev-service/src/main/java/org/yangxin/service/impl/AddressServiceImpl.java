package org.yangxin.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.yangxin.enums.YesNoEnum;
import org.yangxin.mapper.UserAddressMapper;
import org.yangxin.pojo.UserAddress;
import org.yangxin.pojo.query.AddressBO;
import org.yangxin.service.AddressService;

import java.util.Date;
import java.util.List;

/**
 * 地址
 *
 * @author yangxin
 * 2019/12/04 20:54
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
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
    @Transactional(propagation = Propagation.SUPPORTS)
    public UserAddress queryUserAddress(String userId, String addressId) {
        return userAddressMapper.selectByPrimaryKey(addressId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addNewUserAddress(AddressBO addressBO) {
        // 判断当前用户是否存在地址，如果没有，则新增为“默认地址”
        int isDefault = 0;
        List<UserAddress> userAddressList = queryAll(addressBO.getUserId());
        if (CollectionUtils.isEmpty(userAddressList)) {
            isDefault = 1;
        }

        // 保存地址到数据库
        UserAddress userAddress = UserAddress.builder().build();
        BeanUtils.copyProperties(addressBO, userAddress);

        userAddress.setId(sid.nextShort());
        userAddress.setIsDefault(isDefault);
        userAddress.setCreatedTime(new Date());
        userAddress.setUpdatedTime(new Date());
        userAddressMapper.insert(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserAddress(AddressBO addressBO) {
        String addressId = addressBO.getAddressId();

        UserAddress userAddress = UserAddress.builder().build();
        BeanUtils.copyProperties(addressBO, userAddress);

        userAddress.setId(addressId);
        userAddress.setUpdatedTime(new Date());

        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteUserAddress(String addressId) {
        userAddressMapper.deleteByPrimaryKey(addressId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateUserAddressAsDefault(String userId, String addressId) {
        // 查找默认地址，设置为不默认
        List<UserAddress> userAddressList = userAddressMapper.selectByUserId(userId);
        for (UserAddress userAddress : userAddressList) {
            userAddress.setIsDefault(YesNoEnum.NO.getType());
            userAddressMapper.updateByPrimaryKeySelective(userAddress);
        }

        // 根据地址Id，修改其记录为默认的地址
        UserAddress userAddress = UserAddress.builder()
                .id(addressId)
                .userId(userId)
                .isDefault(YesNoEnum.YES.getType())
                .build();
        userAddressMapper.updateByPrimaryKeySelective(userAddress);
    }
}
