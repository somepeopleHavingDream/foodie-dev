package org.yangxin.service.center;

import org.yangxin.pojo.User;
import org.yangxin.pojo.query.center.CenterUserBO;

/**
 * 个人中心用户
 *
 * @author yangxin
 * 2019/11/25 14:32
 */
public interface CenterUserService {

    /**
     * 根据用户Id查询用户信息
     *
     * @param userId 用户Id
     * @return 用户信息
     */
    User queryUserInfo(String userId);

    /**
     * 修改用户信息
     */
    User updateUserInfo(String userId, CenterUserBO centerUserBO);

    /**
     * 用户头像更新
     *
     * @param userId 用户id
     * @param faceUrl 头像url
     */
    User updateUserFace(String userId, String faceUrl);
}
