package com.d.passwordmanager.mapper;

import com.d.passwordmanager.pojo.PasswordRecord;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description:
 * @modify:
 */


//@Repository
public interface PasswordMapper {

//    List<PasswordRecord> selectAll();

    Integer updateById(PasswordRecord passwordRecord);

    Integer insertOne(PasswordRecord passwordRecord);

    Integer deleteByIds(@Param("ids") List<Integer> ids);

    List<PasswordRecord> selectByKeyword(@Param("keyword") String keyword);
}
