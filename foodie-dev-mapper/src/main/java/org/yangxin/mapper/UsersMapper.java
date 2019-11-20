package org.yangxin.mapper;

import org.yangxin.pojo.Users;

public interface UsersMapper {
    int deleteByPrimaryKey(String id);

    int insert(Users record);

    int insertSelective(Users record);

    Users selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Users record);

    int updateByPrimaryKey(Users record);

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
    Users selectByUsernamePassword(String username, String password);
}