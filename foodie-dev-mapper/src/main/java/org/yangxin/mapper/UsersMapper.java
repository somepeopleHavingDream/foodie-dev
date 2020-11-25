package org.yangxin.mapper;

import org.yangxin.pojo.User;

public interface UsersMapper {

    int deleteByPrimaryKey(String id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 判断是否已经有该用户名
     *
     * @param username 用户名
     * @return 记录数
     */
    int selectByUsername(String username);

    /**
     * 通过用户名和密码查询一个用户记录
     *
     * @param username 用户名
     * @param password 密码
     * @return 用户记录
     */
    User selectByUsernamePassword(String username, String password);
}