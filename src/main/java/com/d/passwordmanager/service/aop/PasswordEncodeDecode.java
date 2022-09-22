package com.d.passwordmanager.service.aop;

import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.pojo.User;
import org.aspectj.lang.ProceedingJoinPoint;

import java.util.Arrays;
import java.util.List;

/**
 * @author: Ding
 * @date: 2022/9/19 20:51
 * @description: 对密码进行加密解密的切面类
 * @modify:
 */


public class PasswordEncodeDecode {

    /**
     * 后置通知m，用于密码解密
     *
     * @param pjp
     */
    public Object decode(ProceedingJoinPoint pjp) throws Throwable {
        Object result = pjp.proceed();
        if (result instanceof List) {
            ((List) result).forEach(param -> {
                if (param instanceof PasswordRecord) {
                    PasswordRecord passwordRecord = (PasswordRecord) param;
                    passwordRecord.setPassword(PasswordUtils.decode(passwordRecord.getPassword()));
                } else {
                    throw new RuntimeException("请检查切入点配置");
                }
            });
        } else {
            throw new RuntimeException("请检查切入点配置");
        }
        return result;
    }

    /**
     * 前置通知，用于密码加密
     *
     * @param pjp
     */
    public Object encode(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Arrays.stream(args)
                .filter(param -> param instanceof PasswordRecord)
                .forEach(passwordRecord -> ((PasswordRecord) passwordRecord)
                        .setPassword(PasswordUtils.encode(
                                ((PasswordRecord) passwordRecord).getPassword())));
        return pjp.proceed(args);
    }

    /**
     * 对密码进行强加密的通知
     *
     * @param pjp
     * @return
     * @throws Throwable
     */
    public Object strengthEncode(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        for (int i = 0; i < args.length; i++) {
            Object param = args[i];
            if (param instanceof String) {
                args[i] = PasswordUtils.strengthEncryption((String) param);
            } else if (param instanceof User) {
                User user = (User) param;
                user.setPassword(PasswordUtils.strengthEncryption(user.getPassword()));
                args[i] = user;
            }
        }
        return pjp.proceed(args);
    }
}
