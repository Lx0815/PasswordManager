package com.d.passwordmanager.service.impl;

import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.mapper.PasswordMapper;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.service.PasswordService;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/8/26 10:28
 * @description: 实现了 PasswordService， 完成密码相关的操作
 * @modify:
 */


public class PasswordServiceImpl implements PasswordService {

    private PasswordMapper passwordMapper;
    public void setPasswordMapper(PasswordMapper passwordMapper) {
        this.passwordMapper = passwordMapper;
    }


    @Override
    public List<PasswordRecord> selectAll() {
        return passwordMapper.selectByKeyword(null);
    }


    @Override
    public boolean updateByPasswordRecord(PasswordRecord passwordRecord) {
        return passwordMapper.updateById(passwordRecord) == 1;
    }

    @Override
    public boolean deleteByList(List<PasswordRecord> deleteList) {
        List<Integer> ids = new LinkedList<>();
        deleteList.forEach(passwordRecord -> ids.add(passwordRecord.getId()));
        return passwordMapper.deleteByIds(ids) == ids.size();
    }

    @Override
    public List<PasswordRecord> selectByKeyword(String keyword) {
        return passwordMapper.selectByKeyword(keyword);
    }

    @Override
    public void deleteAll() {
        passwordMapper.deleteAll();
    }

    @Override
    public boolean insertOne(PasswordRecord passwordRecord) {
        return passwordMapper.insertOne(passwordRecord) == 1;
    }
}
