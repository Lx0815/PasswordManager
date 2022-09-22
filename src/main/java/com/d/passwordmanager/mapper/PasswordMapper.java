package com.d.passwordmanager.mapper;

import com.d.passwordmanager.pojo.PasswordRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description:
 * @modify:
 */


public interface PasswordMapper {

    Integer updateById(PasswordRecord passwordRecord);

    Integer insertOne(PasswordRecord passwordRecord);

    Integer deleteByIds(@Param("ids") List<Integer> ids);

    List<PasswordRecord> selectByKeyword(@Param("keyword") String keyword);

    void deleteAll();

    Integer insertByList(@Param("list") List<PasswordRecord> list);
}
