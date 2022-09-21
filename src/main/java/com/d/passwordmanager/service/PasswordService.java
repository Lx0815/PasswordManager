package com.d.passwordmanager.service;

import com.d.passwordmanager.pojo.PasswordRecord;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author: Ding
 * @date: 2022/8/26 10:26
 * @description: 处理密码相关的需求
 * @modify:
 */


public interface PasswordService {
    List<PasswordRecord> selectAll();

    boolean insertOne(PasswordRecord passwordRecord);

    boolean updateByPasswordRecord(PasswordRecord rowValue);

    boolean deleteByList(List<PasswordRecord> deleteList);

    List<PasswordRecord> selectByKeyword(String keyword);

    void deleteAll();

    boolean importFromEdge(File file, Map<String, String> mapperMap);

    boolean savaToFile(File file, PasswordService passwordService);
}
