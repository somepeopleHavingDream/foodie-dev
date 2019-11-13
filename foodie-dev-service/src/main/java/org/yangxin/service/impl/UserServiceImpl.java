package org.yangxin.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.yangxin.service.UserService;

/**
 * @author yangxin
 * 2019/11/13 21:35
 */
@Service
public class UserServiceImpl implements UserService {
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public boolean queryUsernameIsExist(String username) {
        return false;
    }
}
