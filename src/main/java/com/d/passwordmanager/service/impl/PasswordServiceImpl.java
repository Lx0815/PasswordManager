package com.d.passwordmanager.service.impl;

import com.d.passwordmanager.command.utils.CsvUtils;
import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.mapper.PasswordMapper;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.service.PasswordService;
import org.springframework.util.ObjectUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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
    public boolean insertOne(PasswordRecord passwordRecord) {
        return passwordMapper.insertOne(passwordRecord) == 1;
    }

    @Override
    public boolean importByCsv(File file, Map<String, String> mapperMap) {
        try {
            List<PasswordRecord> passwordRecordList = CsvUtils.readAll(PasswordRecord.class,
                    mapperMap,
                    file.toPath());

            passwordRecordList.forEach(it -> {
                // 设置密码强度
                it.setPasswordStrength(PasswordUtils.calculatePasswordStrength(it.getPassword()));
                // 加密密码
                it.setPassword(PasswordUtils.encode(it.getPassword()));
            });

            if (ObjectUtils.isEmpty(passwordRecordList)) return true;
            Integer count = passwordMapper.insertByList(passwordRecordList);

            return ObjectUtils.nullSafeEquals(count, passwordRecordList.size());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean savaToFile(File file, PasswordService passwordService) {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            // 通过 passwordService 被增强后的类去调用方法，以获取到解密之后的密码
            List<PasswordRecord> list = passwordService.selectAll();
            StringBuilder sb = new StringBuilder("name,url,username,password\n");
            list.forEach(it -> {
                sb.append(it.getDomainName())
                        .append(',')
                        .append(it.getDescription())
                        .append(',')
                        .append(it.getUsername())
                        .append(',')
                        .append(it.getPassword())
                        .append('\n');
            });

            outputStream.write(sb.toString().getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
            return true;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
