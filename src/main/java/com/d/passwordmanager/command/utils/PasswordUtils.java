package com.d.passwordmanager.command.utils;

import com.d.passwordmanager.command.constant.PasswordStrength;
import com.d.passwordmanager.pojo.PasswordRecord;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.util.ObjectUtils;

import java.util.regex.Pattern;
import static com.d.passwordmanager.command.constant.PasswordStrength.*;

/**
 * @author: Ding
 * @date: 2022/9/17 10:35
 * @description: 操作密码相关的工具类
 * @modify:
 */


public class PasswordUtils {

    private PasswordUtils() {}

    /**
     * 允许使用的所有字符
     */
    public static final String ALLOW_STRINGS = "\"QWERTYUIOPASDFGHJKLZXCVBNMabcdefghijklmnopqrstuvwxyz0123456789`~!@#$%^&*()_+-=[]{}\\\\|;:'\\\",<.>/?\"";

    /**
     * 允许使用的所有字符
     */
    public static final char[] ALLOW_CHARS = new char[]{'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '`', '~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '+', '-', '=', '[', ']', '{', '}', '\\', '|', ';', ':', '\'', '\"', ',', '<', '.', '>', '/', '?'};

    /**
     * 匹配 Unicode 符号的正则表达式
     */
    public static final String SYMBOLS_EXP = "[`~!@#$%^&*()_+\\-=\\[\\]{}\\\\|;:'\",<.>/?]";

    /**
     * 匹配 数字 的正则表达式
     */
    public static final String DIGITS_EXP = "\\d";

    /**
     * 匹配大小写字母的正则表达式
     */
    public static final String LETTERS_EXP = "[a-zA-Z]";

    /**
     * 校验密码是否正确，即校验密码是否都由 {@link #ALLOW_CHARS} 中的字符所组成
     *
     * @param password 密码
     * @return 密码正确则返回 true
     */
    public static boolean checkPassword(String password) {
        int len = password.length();
        for (int i = 0; i < len; i++) {
            if (! ALLOW_STRINGS.contains(String.valueOf(password.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 从与 {@link PasswordRecord} 的可编辑字段对应的 TextField 输入框中获取密码对象
     *
     * @param domainNameTextField 域名字段输入框
     * @param descriptionTextField 描述字段输入框
     * @param accountTextField 账户字段输入框
     * @param passwordTextField 面膜字段输入框
     * @return 返回用户输入的 {@link PasswordRecord} 对象
     */
    public static PasswordRecord getPasswordRecordByTextFields(TextField domainNameTextField,
                                                               TextField descriptionTextField,
                                                               TextField accountTextField,
                                                               TextField passwordTextField) {
        String domainName = domainNameTextField.getText();
        String description = descriptionTextField.getText();
        String account = accountTextField.getText();
        String password = passwordTextField.getText();

        if (ObjectUtils.isEmpty(domainName) || ObjectUtils.isEmpty(description)
                || ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(password)) {

            AlertUtils.alert(Alert.AlertType.WARNING, "请完善相关信息");
            return null;
        }
        if (!checkPassword(password)) {
            AlertUtils.alert(Alert.AlertType.WARNING, "密码格式错误，请使用大小写英文字母、数字以及下列符号`~!@#$%^&*()_+-=[]{}\\\\|;:'\\\",<.>/?\"");
        }

        PasswordStrength passwordStrength = calculatePasswordStrength(password);

        return new PasswordRecord(domainName, description, account, password, passwordStrength);
    }

    /**
     * 计算密码强度。
     * 密码含有 数字、大小写字母、符号 之一时，强度为 {@link PasswordStrength#WEAK}
     * 密码含有 数字、大小写字母、符号 之二时，强度为 {@link PasswordStrength#MEDIUM}
     * 密码含有 数字、大小写字母、符号 三者时，强度为 {@link PasswordStrength#STRENGTH}
     *
     * @param password 密码
     * @return 返回该密码的强度
     */
    public static PasswordStrength calculatePasswordStrength(String password) {
        int kind = 0;

        if (Pattern.compile(DIGITS_EXP).matcher(password).find()) {
            ++kind;
        }
        if (Pattern.compile(SYMBOLS_EXP).matcher(password).find()) {
            ++kind;
        }
        if (Pattern.compile(LETTERS_EXP).matcher(password).find()) {
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

    /**
     * 移出没有被修改的属性值
     *
     * @param newPasswordRecord 新的对象
     * @param oldPasswordRecord 旧的对象
     */
    public static void removeNotEditProperty(PasswordRecord newPasswordRecord, PasswordRecord oldPasswordRecord) {
        if (ObjectUtils.nullSafeEquals(newPasswordRecord.getDomainName(), oldPasswordRecord.getDomainName())) {
            newPasswordRecord.setDomainName(null);
        }
        if (ObjectUtils.nullSafeEquals(newPasswordRecord.getDescription(), oldPasswordRecord.getDescription())) {
            newPasswordRecord.setDescription(null);
        }
        if (ObjectUtils.nullSafeEquals(newPasswordRecord.getPassword(), oldPasswordRecord.getPassword())) {
            newPasswordRecord.setPassword(null);
        }
        if (ObjectUtils.nullSafeEquals(newPasswordRecord.getAccount(), oldPasswordRecord.getAccount())) {
            newPasswordRecord.setAccount(null);
        }
    }
}
