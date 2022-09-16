package com.d.passwordmanager.service.impl;

import com.d.passwordmanager.mapper.PasswordMapper;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.service.PasswordService;
import org.springframework.util.ObjectUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/8/26 10:28
 * @description:
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
    public boolean updateDescription(PasswordRecord passwordRecord) {
        passwordRecord.setPassword(null);
        passwordRecord.setPasswordStrength(null);
        passwordRecord.setDomainName(null);
        passwordRecord.setAccount(null);
        return ObjectUtils.nullSafeEquals(passwordMapper.updateById(passwordRecord), 1);
    }

    @Override
    public boolean updateDomainName(PasswordRecord passwordRecord) {
        passwordRecord.setPassword(null);
        passwordRecord.setPasswordStrength(null);
        passwordRecord.setDescription(null);
        passwordRecord.setAccount(null);
        return ObjectUtils.nullSafeEquals(passwordMapper.updateById(passwordRecord), 1);
    }

    @Override
    public boolean updatePassword(PasswordRecord passwordRecord) {
        passwordRecord.setDomainName(null);
        passwordRecord.setPasswordStrength(null);
        passwordRecord.setDescription(null);
        passwordRecord.setAccount(null);
        return ObjectUtils.nullSafeEquals(passwordMapper.updateById(passwordRecord), 1);
    }


    @Override
    public boolean updateAccount(PasswordRecord passwordRecord) {
        passwordRecord.setDomainName(null);
        passwordRecord.setPasswordStrength(null);
        passwordRecord.setDescription(null);
        passwordRecord.setPassword(null);
        return ObjectUtils.nullSafeEquals(passwordMapper.updateById(passwordRecord), 1);
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
    public boolean insertOne(PasswordRecord passwordRecord) {
        return passwordMapper.insertOne(passwordRecord) == 1;
    }
}
