package org.yangxin.service.impl.center;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.mapper.UsersMapper;
import org.yangxin.pojo.User;
import org.yangxin.service.center.CenterUserService;

/**
 * @author yangxin
 * 2019/11/13 21:35
 */
@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Service
public class CenterUserServiceImpl implements CenterUserService {

    private final UsersMapper usersMapper;

    @Autowired
    public CenterUserServiceImpl(UsersMapper usersMapper) {
        this.usersMapper = usersMapper;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User queryUserInfo(String userId) {
        User user = usersMapper.selectByPrimaryKey(userId);
        user.setPassword(null);

        return user;
    }
}
