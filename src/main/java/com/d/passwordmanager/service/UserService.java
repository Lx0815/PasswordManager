package com.d.passwordmanager.service;

/**
 * @author: Ding
 * @date: 2022/8/20 15:20
 * @description:
 * @modify:
 */


public interface UserService {

    boolean login(String password);

    Integer getErrorCount();

    void setErrorCount(Integer newErrorCount);

    void deleteUser();

    boolean register(String passwordStr, String emailStr);

    Integer selectCount();
}
