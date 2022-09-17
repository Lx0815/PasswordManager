package com.d.passwordmanager.controller;

/**
 * @author: Ding
 * @date: 2022/9/17 8:56
 * @description: EditPassword 对应的控制器
 * @modify:
 */

import java.net.URL;
import java.util.ResourceBundle;
import static com.d.passwordmanager.command.utils.PasswordUtils.*;

import com.d.passwordmanager.command.constant.PasswordStrength;
import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.service.PasswordService;
import com.d.passwordmanager.views.EditPasswordView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.util.Assert;

public class EditPasswordController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="accountTextField"
    private TextField accountTextField; // Value injected by FXMLLoader

    @FXML // fx:id="commitEditButton"
    private Button commitEditButton; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTextField"
    private TextField descriptionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="domainNameTextField"
    private TextField domainNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="nowPasswordStrength"
    private Label passwordTips; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private TextField passwordTextField; // Value injected by FXMLLoader


    /* Spring */

    private PasswordService passwordService;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }


    private EditPasswordView editPasswordView;

    public void setEditPasswordView(EditPasswordView editPasswordView) {
        this.editPasswordView = editPasswordView;
    }

    private IndexController indexController;

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    /* Others */
    private PasswordRecord oldPasswordRecord;

    public void setOldPasswordRecord(PasswordRecord oldPasswordRecord) {
        this.oldPasswordRecord = oldPasswordRecord;
    }

    /**
     * 当 提交修改 按钮 被点击时调用
     *
     * @param event 鼠标事件
     */
    @FXML
    void commitEdit(MouseEvent event) {
        PasswordRecord passwordRecord = getNewPasswordRecord();

        boolean isSuccess = passwordService.updateByPasswordRecord(passwordRecord);
        if (isSuccess) {
            editPasswordView.close();
            AlertUtils.alert(Alert.AlertType.INFORMATION, "密码修改成功");
            indexController.refresh();
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "密码修改失败，请联系管理员处理");
        }
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert accountTextField != null : "fx:id=\"accountTextField\" was not injected: check your FXML file 'editPassword.fxml'.";
        assert commitEditButton != null : "fx:id=\"commitEditButton\" was not injected: check your FXML file 'editPassword.fxml'.";
        assert descriptionTextField != null : "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'editPassword.fxml'.";
        assert domainNameTextField != null : "fx:id=\"domainNameTextField\" was not injected: check your FXML file 'editPassword.fxml'.";
        assert passwordTips != null : "fx:id=\"nowPasswordStrength\" was not injected: check your FXML file 'editPassword.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'editPassword.fxml'.";

        passwordTextField.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (oldValue && !newValue) {
                //添加事件
                String password = passwordTextField.getText();
                char[] chars = password.toCharArray();
                for (int i = 0; i < chars.length; i++) {
                    if (chars[i] > '~') {
                        passwordTips.setText("密码格式错误，您的密码应仅由数字、大小写字母、以及以下符号组成：\n" + PasswordUtils.ALLOW_STRINGS);
                    }
                }
                PasswordStrength passwordStrength = calculatePasswordStrength(password);
                passwordTips.setText("当前密码强度为：" + passwordStrength.name());
            }
        });

        // 初始化填充上用户选择的密码记录的相关信息
        domainNameTextField.setText(oldPasswordRecord.getDomainName());
        descriptionTextField.setText(oldPasswordRecord.getDescription());
        accountTextField.setText(oldPasswordRecord.getAccount());
        passwordTextField.setText(oldPasswordRecord.getPassword());
        passwordTips.setText("当前密码强度为：" + oldPasswordRecord.getPasswordStrength().name());

    }

    /**
     * 获取密码记录
     *
     * @return
     */
    private PasswordRecord getNewPasswordRecord() {

        PasswordRecord passwordRecord = getPasswordRecordByTextFields(domainNameTextField, descriptionTextField, accountTextField, passwordTextField);
        // 此断言不应该有效
        Assert.notNull(passwordRecord, "无法获取密码对象");

        PasswordUtils.removeNotEditProperty(passwordRecord, oldPasswordRecord);

        passwordRecord.setId(oldPasswordRecord.getId());
        return passwordRecord;
    }
}
