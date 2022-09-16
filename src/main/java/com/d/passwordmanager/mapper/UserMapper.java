package com.d.passwordmanager.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description:
 * @modify:
 */


//@Repository
public interface UserMapper {

    Integer selectIdByPassword(@Param("password") String password);

    Integer selectErrorCount();

    void updateErrorCount(@Param("newErrorCount") Integer newErrorCount);

    void deleteAll();

    Integer insertOne(@Param("password") String password,
                      @Param("email") String email);

    Integer selectCount();

}
