package com.d.passwordmanager.service.impl;

import static com.d.passwordmanager.command.constant.ErrorCode.*;
import com.d.passwordmanager.mapper.UserMapper;
import com.d.passwordmanager.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Objects;

/**
 * @author: Ding
 * @date: 2022/8/20 15:24
 * @description: UserService 的实现类，
 * @modify:
 */


public class UserServiceImpl implements UserService {
    private UserMapper userMapper;

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String password) {
        Integer id = userMapper.selectIdByPassword(password);
        return Objects.nonNull(id);
    }

    @Override
    public Integer getErrorCount() {
        Integer errorCount = userMapper.selectErrorCount();
        return Objects.requireNonNullElse(errorCount, NO_DATA.toInteger());
    }

    @Override
    public void setErrorCount(Integer newErrorCount) {
        userMapper.updateErrorCount(newErrorCount);
    }

    @Override
    public void deleteUser() {
        userMapper.deleteAll();
    }

    @Override
    public boolean register(String password, String email) {
        Integer rows = userMapper.insertOne(password, email);
        return ObjectUtils.nullSafeEquals(rows, 1);
    }

    @Override
    public Integer selectCount() {
        return userMapper.selectCount();
    }
}
