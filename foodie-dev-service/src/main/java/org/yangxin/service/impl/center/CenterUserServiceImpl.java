package org.yangxin.service.impl.center;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.SexEnum;
import org.yangxin.mapper.UsersMapper;
import org.yangxin.pojo.Users;
import org.yangxin.pojo.query.UserQuery;
import org.yangxin.service.UserService;
import org.yangxin.service.center.CenterUserService;
import org.yangxin.utils.DateUtil;
import org.yangxin.utils.MD5Util;

import java.security.NoSuchAlgorithmException;
import java.util.Date;

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
    public Users queryUserInfo(String userId) {
        Users user = usersMapper.selectByPrimaryKey(userId);
        user.setPassword(null);

        return user;
    }
}
