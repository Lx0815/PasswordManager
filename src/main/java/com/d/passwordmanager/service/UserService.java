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

    /**
     * 处理登录请求
     *
     * @param password 用户登陆时输入的密码
     * @return 返回 true 表示登录成功
     */
    boolean login(String password);

    /**
     * 处理注册请求
     *
     * @param user 要注册的用户
     * @return 返回是否注册成功
     */
    boolean register(User user);

    /**
     * 查询用户的所有密保问题
     *
     * @return 返回用户的所有密保问题
     */
    List<String> selectQuestions();

    /**
     * 检查用户输入的答案是否与密保问题的答案相同
     *
     * @param answer1 第一个密保问题的答案
     * @param answer2 第二个密保问题的答案
     * @param answer3 第三个密保问题的答案
     * @return 返回答案是否输入正确
     */
    boolean checkAnswer(String answer1, String answer2, String answer3);

    /**
     * 更新用户密码
     *
     * @param password 新的密码
     * @return 返回是否更新成功
     */
    boolean updatePassword(String password);

    /**
     * 查询用户数量，用于判断是否已注册过用户
     *
     * @return 返回 1 或 0，1表示用户已注册
     */
    Integer selectUserCount();

}
