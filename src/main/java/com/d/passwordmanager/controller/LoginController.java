package com.d.passwordmanager.controller;

import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.service.UserService;
import com.d.passwordmanager.views.FindPasswordView;
import com.d.passwordmanager.views.IndexView;
import com.d.passwordmanager.views.LoginView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Ding
 * @date: 2022/8/25 8:32
 * @description: loginView 对应的控制器
 * @modify:
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

    /**
     * 由 spring 注入
     */

    private UserService userService;
    private LoginView loginView;
    private IndexView indexView;
    private FindPasswordView findPasswordView;

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

    public void setFindPasswordView(FindPasswordView findPasswordView) {
        this.findPasswordView = findPasswordView;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
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

    /**
     * fx:id="loginButton"
     * 点击登录按钮时调用
     *
     * @param mouseEvent 鼠标点击事件
     */
    @FXML
    public void login(MouseEvent mouseEvent) {
        String passwordStr = passwordField.getText();
        boolean isSuccess = userService.login(passwordStr);
        if (isSuccess) {
            toIndexView();
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "登录失败", "密码错误");
        }
    }

    /**
     * 转到主页面
     */
    private void toIndexView() {
        ApplicationUtils.startAndShow(indexView);
        loginView.close();
    }

    /**
     * 转到 找回密码的页面
     *
     * @param mouseEvent 鼠标点击事件
     */
    @FXML
    public void toFindPassword(MouseEvent mouseEvent) {
        ApplicationUtils.startAndShow(findPasswordView);
    }
}
