package com.d.passwordmanager.service;

import com.d.passwordmanager.pojo.User;

import java.util.List;

/**
 * @author: Ding
 * @date: 2022/8/20 15:20
 * @description: 处理用户相关的需求
 * @modify:
 */


public interface UserService {

    boolean login(String password);

    boolean register(User user);

    List<String> selectQuestions();

    boolean checkAnswer(String answer1, String answer2, String answer3);

    boolean updatePassword(String password);

    Integer selectUserCount();

}
