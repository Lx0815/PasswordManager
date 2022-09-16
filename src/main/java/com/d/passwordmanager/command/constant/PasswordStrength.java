package com.d.passwordmanager.command.constant;

import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description:
 * @modify:
 */


public enum PasswordStrength implements Comparator<PasswordStrength> {

    STRENGTH(1),

    MEDIUM(0),

    WEAK(-1);

    private final Integer strength;

    public static final String ALLOW_STRINGS = "\"QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-=[]{}\\\\|;:'\\\",<.>/?\"";

    public static final char[] ALLOW_CHARS = new char[]{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', '\\', '|', ';', ':', '\'', '\"', ',', '<', '.', '>', '/', '?'};
    private static final String symbol = "[`~!@#$%^&*()_+\\-=\\[\\]{}\\\\|;:'\",<.>/?]";
    private static final String digit = "\\d";
    private static final String letter = "[a-zA-Z]";

    PasswordStrength(Integer strength) {
        this.strength = strength;
    }

    public static PasswordStrength calculatePasswordStrength(String password) {
        int kind = 0;

        if (Pattern.compile(digit).matcher(password).find()) {
            ++kind;
        }
        if (Pattern.compile(symbol).matcher(password).find()) {
            ++kind;
        }
        if (Pattern.compile(letter).matcher(password).find()) {
            ++kind;
        }

        switch (kind) {
            case 1:
                return WEAK;
            case 2:
                return MEDIUM;
            case 3:
                return STRENGTH;
            default:
                // 此异常不应被抛出
                throw new RuntimeException("密码校验失败");
        }
    }


    @Override
    public int compare(PasswordStrength o1, PasswordStrength o2) {
        return o1.strength - o2.strength;
    }
}
