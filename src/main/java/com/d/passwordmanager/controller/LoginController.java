package com.d.passwordmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.service.UserService;
import com.d.passwordmanager.views.IndexView;
import com.d.passwordmanager.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static com.d.passwordmanager.command.constant.ErrorCode.NO_DATA;

/**
 * Sample Skeleton for 'login.fxml' Controller Class
 */

public class LoginController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="loginButton"
    private Button loginButton; // Value injected by FXMLLoader

    @FXML // fx:id="loginPanel"
    private Pane loginPanel; // Value injected by FXMLLoader

    @FXML // fx:id="passwordField"
    private PasswordField passwordField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTipsLabel"
    private Label passwordTipsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="titleLabel"
    private Label titleLabel; // Value injected by FXMLLoader

    /*
     * 由 spring 注入
     */
    private UserService userService;

    private LoginView loginView;

    private IndexView indexView;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginPanel != null : "fx:id=\"loginPanel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordField != null : "fx:id=\"passwordField\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordTipsLabel != null : "fx:id=\"passwordTipsLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'login.fxml'.";

    }

    public double getPrefHeight() {
        return loginPanel.getPrefHeight();
    }

    public double getPrefWidth() {
        return loginPanel.getPrefWidth();
    }

    @FXML
    public void login(MouseEvent mouseEvent) {
        String passwordStr = passwordField.getText();
        boolean isSuccess = userService.login(passwordStr);
        if (isSuccess) {
            toIndexView();
        } else {
            int errorCount = updateErrorCount();
            if (errorCount < 3 && errorCount > 0) {
                AlertUtils.alert(Alert.AlertType.WARNING, "登录失败", "密码错误，您还剩 " + (3 - errorCount) + " 次机会");
            } else {
                AlertUtils.alert(Alert.AlertType.WARNING, "登录失败", "密码第三次输入错误，为保护数据安全，仅存的备份将会通过绑定邮箱发送给您，请注意查收。当前本机上所有数据已自动销毁。");
            }
        }
    }


    private Integer updateErrorCount() {
        Integer oldErrorCount = userService.getErrorCount();
        if (Objects.equals(oldErrorCount, NO_DATA.toInteger())) {
            // 先注册用户
        }
        if (oldErrorCount == 2) {
            // 发送所有数据到用户邮箱
            // 清空数据
            deleteAll();
        }
        Integer newErrorCount = oldErrorCount + 1;
        userService.setErrorCount(newErrorCount);
        return newErrorCount;
    }

    private void deleteAll() {
        // 清空用户表
        userService.deleteUser();
    }

    private void toIndexView() {
        // do something
        try {
            indexView.start(new Stage());
            loginView.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /* IOC setter 注入 */
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    public void setIndexView(IndexView indexView) {
        this.indexView = indexView;
    }
}
