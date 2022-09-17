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
 * @description:
 * @modify:
 */


public class PasswordUtils {

    private PasswordUtils() {}

    public static boolean checkPassword(String password) {
        int len = password.length();
        for (int i = 0; i < len; i++) {
            if (! PasswordStrength.ALLOW_STRINGS.contains(String.valueOf(password.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

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


}
