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
    public static final String SYMBOLS_EXP = "[`~!@#$%^&*()_+\\-=\\[\\]{}\\\\|;:'\",<.>/?]";
    public static final String DIGITS_EXP = "\\d";
    public static final String LETTERS_EXP = "[a-zA-Z]";

    PasswordStrength(Integer strength) {
        this.strength = strength;
    }



    @Override
    public int compare(PasswordStrength o1, PasswordStrength o2) {
        return o1.strength - o2.strength;
    }

}
