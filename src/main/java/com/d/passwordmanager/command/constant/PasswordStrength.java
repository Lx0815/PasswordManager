package com.d.passwordmanager.command.constant;

import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description: 密码强度枚举类
 * @modify:
 */


public enum PasswordStrength implements Comparator<PasswordStrength> {

    /**
     * 强密码
     */
    STRENGTH(1),

    /**
     * 中等密码
     */
    MEDIUM(0),

    /**
     * 弱密码
     */
    WEAK(-1);


    private final Integer strength;

    PasswordStrength(Integer strength) {
        this.strength = strength;
    }

    @Override
    public int compare(PasswordStrength o1, PasswordStrength o2) {
        return o1.strength - o2.strength;
    }
}
