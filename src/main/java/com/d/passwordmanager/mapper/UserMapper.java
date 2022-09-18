package com.d.passwordmanager.mapper;

import com.d.passwordmanager.pojo.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description:
 * @modify:
 */


public interface UserMapper {

    User selectUser();

    Integer insertOne(User user);

    Integer selectId();

    Integer updateById(User user);

    Integer selectUserCount();

    Integer selectCountByPassword(@Param("password") String password);
}
