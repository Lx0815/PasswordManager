package com.d.passwordmanager.command.constant;

/**
 * @author: Ding
 * @date: 2022/8/20 23:05
 * @description:
 * @modify:
 */


public enum ErrorCode {

    NO_DATA(-1);

    private final Object error;

    public Integer toInteger() {
        if (! (error instanceof Integer)) {
            throw new RuntimeException("枚举" + this + "调用了错误的转换方法");
        }
        // 一定成功
        return (Integer) this.error;
    }

    ErrorCode(Object error) {
        this.error = error;
    }
}
