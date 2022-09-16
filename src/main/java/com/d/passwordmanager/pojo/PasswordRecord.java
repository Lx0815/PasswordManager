package com.d.passwordmanager.pojo;

import com.d.passwordmanager.command.constant.PasswordStrength;
import com.d.passwordmanager.controller.selfcontroller.MyStyleTextFieldOfPassWord;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import org.springframework.util.ObjectUtils;

import static com.d.passwordmanager.controller.selfcontroller.TextFieldFocusListener.getEcho;

/**
 * @author: Ding
 * @date: 2022/8/26 9:55
 * @description:
 * @modify:
 */


public class PasswordRecord {

    private Integer id;

    private String domainName;

    private String description;

    private String account;

    private String password;

    // 仅用于记录当前密码记录是否被选中
    private CheckBox selected = new CheckBox();

    private TextField passwordField;

    private PasswordStrength passwordStrength;

    public PasswordRecord() {}

    public PasswordRecord(Integer id, String domainName, String description, String account, String password, PasswordStrength passwordStrength) {
        this.id = id;
        this.domainName = domainName;
        this.description = description;
        this.account = account;
        this.password = password;
        this.passwordStrength = passwordStrength;
    }

    public PasswordRecord(String domainName, String description, String account, String password, PasswordStrength passwordStrength) {
        this(null, domainName, description, account, password, passwordStrength);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PasswordStrength getPasswordStrength() {
        return passwordStrength;
    }

    public void setPasswordStrength(PasswordStrength passwordStrength) {
        this.passwordStrength = passwordStrength;
    }

    public CheckBox getSelected() {
        return selected;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    public TextField getPasswordField() {
        if (ObjectUtils.isEmpty(passwordField)) {
            MyStyleTextFieldOfPassWord myStyleTextFieldOfPassWord = new MyStyleTextFieldOfPassWord();
            myStyleTextFieldOfPassWord.setPassword(password);

            String echo = getEcho(password);
            myStyleTextFieldOfPassWord.setEcho(echo);

            passwordField = myStyleTextFieldOfPassWord.getPasswordField();
            passwordField.setText(echo);
        }
        return passwordField;
    }

    public void setPasswordField(TextField passwordField) {
        this.passwordField = passwordField;
    }

    @Override
    public String toString() {
        return "PasswordRecord{" +
                "id=" + id +
                ", domainName='" + domainName + '\'' +
                ", description='" + description + '\'' +
                ", password='" + password + '\'' +
                ", passwordStrength=" + passwordStrength +
                '}';
    }
}
