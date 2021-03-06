package org.yangxin.service.impl.center;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.mapper.UserMapper;
import org.yangxin.pojo.User;
import org.yangxin.pojo.bo.center.CenterUserBO;
import org.yangxin.service.center.CenterUserService;

import java.util.Date;

/**
 * @author yangxin
 * 2019/11/13 21:35
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CenterUserServiceImpl implements CenterUserService {

    private final UserMapper userMapper;

    @Autowired
    public CenterUserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserInfo(String userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        user.setPassword(null);

        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUserInfo(String userId, CenterUserBO centerUserBO) {
        User updateUser = new User();
        BeanUtils.copyProperties(centerUserBO, updateUser);
        updateUser.setId(userId);
        updateUser.setUpdatedTime(new Date());

        userMapper.updateByPrimaryKeySelective(updateUser);

        return queryUserInfo(userId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User updateUserFace(String userId, String faceUrl) {
        User user = User.builder()
                .id(userId)
                .face(faceUrl)
                .updatedTime(new Date())
                .build();
        userMapper.updateByPrimaryKeySelective(user);

        return queryUserInfo(userId);
    }
}
