package com.d.passwordmanager.service;

import com.d.passwordmanager.pojo.PasswordRecord;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/8/26 10:26
 * @description:
 * @modify:
 */


public interface PasswordService {
    List<PasswordRecord> selectAll();

    boolean insertOne(PasswordRecord passwordRecord);

    boolean updateByPasswordRecord(PasswordRecord rowValue);

    boolean deleteByList(List<PasswordRecord> deleteList);

    List<PasswordRecord> selectByKeyword(String keyword);
}
