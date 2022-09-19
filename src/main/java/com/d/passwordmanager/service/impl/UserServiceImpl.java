package com.d.passwordmanager.service.impl;

import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.mapper.UserMapper;
import com.d.passwordmanager.pojo.User;
import com.d.passwordmanager.service.UserService;
import org.springframework.util.ObjectUtils;

import java.util.List;
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
        Integer count = userMapper.selectCountByPassword(password);
        return ObjectUtils.nullSafeEquals(count, 1);
    }

    @Override
    public boolean register(User user) {
        return userMapper.insertOne(user) == 1;
    }

    @Override
    public List<String> selectQuestions() {
        return userMapper.selectUser().getQuestions();
    }

    @Override
    public boolean checkAnswer(String answer1, String answer2, String answer3) {
        User user = userMapper.selectUser();
        return (ObjectUtils.nullSafeEquals(user.getAnswer1(), answer1))
                && (ObjectUtils.nullSafeEquals(user.getAnswer2(), answer2))
                && (ObjectUtils.nullSafeEquals(user.getAnswer3(), answer3));
    }

    @Override
    public boolean updatePassword(String password) {
        Integer id = userMapper.selectId();
        User user = new User();
        user.setPassword(password);
        user.setId(id);
        return userMapper.updateById(user) == 1;
    }

    @Override
    public Integer selectUserCount() {
        return userMapper.selectUserCount();
    }
}
