package org.yangxin.service.impl;

import org.n3r.idworker.Sid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.enums.SexEnum;
import org.yangxin.mapper.UsersMapper;
import org.yangxin.pojo.User;
import org.yangxin.pojo.query.UserBO;
import org.yangxin.service.UserService;
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
public class UserServiceImpl implements UserService {
    private final UsersMapper usersMapper;

    private final Sid sid;

    /**
     * 默认用户头像地址
     */
    private static final String USER_FACE = "http://122.152.205.72:88/group1/M00/00/05/CpoxxFw_8_qAIlFXAAAcIhVPdSg994.png";

    @Autowired
    public UserServiceImpl(UsersMapper usersMapper, Sid sid) {
        this.usersMapper = usersMapper;
        this.sid = sid;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        return usersMapper.selectByUsername(username) > 0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public User createUser(UserBO userBO) {
        try {
            User user = User.builder()
                    // id
                    .id(sid.nextShort())
                    // 用户名
                    .username(userBO.getUsername())
                    // 密码
                    .password(MD5Util.getMD5Str(userBO.getPassword()))
                    // 昵称
                    .nickname(userBO.getUsername())
                    // 头像
                    .face(USER_FACE)
                    // 生日
                    .birthday(DateUtil.stringToDate("1900-01-01"))
                    // 性别
                    .sex(SexEnum.SECRET.getType())
                    // 创建时间
                    .createdTime(new Date())
                    // 更新时间
                    .updatedTime(new Date())
                    .build();

            usersMapper.insert(user);
            return user;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public User queryUserForLogin(String username, String password) {
        return usersMapper.selectByUsernamePassword(username, password);
    }
}
