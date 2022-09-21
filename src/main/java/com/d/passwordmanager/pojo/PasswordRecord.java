package com.d.passwordmanager.pojo;

import com.d.passwordmanager.command.constant.PasswordStrength;
import javafx.scene.control.CheckBox;
import org.springframework.util.ObjectUtils;


/**
 * @author: Ding
 * @date: 2022/8/26 9:55
 * @description: 密码记录实体类， 对应 表password_record
 * @modify:
 */


public class PasswordRecord {

    private Integer id;

    private String domainName;

    private String description;

    private String username;

    private String password;

    // 仅用于记录当前密码记录是否被选中
    private CheckBox selected = new CheckBox();

    // 仅用于隐藏密码时的替代显示
    private String passwordEcho;

    private PasswordStrength passwordStrength;

    public PasswordRecord() {}

    public PasswordRecord(Integer id, String domainName, String description, String username, String password, PasswordStrength passwordStrength) {
        this.id = id;
        this.domainName = domainName;
        this.description = description;
        this.username = username;
        this.password = password;
        this.passwordStrength = passwordStrength;
    }

    public PasswordRecord(String domainName, String description, String username, String password, PasswordStrength passwordStrength) {
        this(null, domainName, description, username, password, passwordStrength);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
//        return null;
    }

    public void setSelected(CheckBox selected) {
        this.selected = selected;
    }

    public String getPasswordEcho() {
        if (ObjectUtils.isEmpty(passwordEcho)) {
            passwordEcho = "*".repeat(password.length());
        }
        return passwordEcho;
    }

    public void setPasswordEcho(String passwordEcho) {
        this.passwordEcho = passwordEcho;
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
