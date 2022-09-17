package com.d.passwordmanager.command.constant;

/**
 * @author: Ding
 * @date: 2022/8/20 23:05
 * @description: 错误码
 * @modify:
 */


public enum ErrorCode {

    NO_DATA(-1);

    private final int error;

    public int toInteger() {
        return this.error;
    }

    ErrorCode(int error) {
        this.error = error;
    }
}
