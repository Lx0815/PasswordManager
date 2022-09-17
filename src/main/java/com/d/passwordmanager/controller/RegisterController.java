package com.d.passwordmanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.service.UserService;
import com.d.passwordmanager.service.impl.UserServiceImpl;
import com.d.passwordmanager.views.LoginView;
import com.d.passwordmanager.views.RegisterView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * @author: Ding
 * @date: 2022/8/25 8:32
 * @description: registerView 对应的控制器
 * @modify:
 */


public class RegisterController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="emailTextField"
    private TextField emailTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private TextField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="registerPanel"
    private Pane registerPanel; // Value injected by FXMLLoader

    private static final String EMAIL_REGEX = "^([a-z\\d_.-]+)@([\\da-z.-]+)\\.([a-z.]{2,6})$";

    /* Spring */
    private UserService userService;
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    private LoginView loginView;
    public void setLoginView(LoginView loginView) {
        this.loginView = loginView;
    }

    private RegisterView registerView;
    public void setRegisterView(RegisterView registerView) {
        this.registerView = registerView;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert emailTextField != null : "fx:id=\"emailTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'register.fxml'.";
        assert registerPanel != null : "fx:id=\"registerPanel\" was not injected: check your FXML file 'register.fxml'.";
    }

    /**
     * 进行注册操作
     *
     * @param mouseEvent
     */
    public void doRegister(MouseEvent mouseEvent) {
        String passwordStr = passwordTextField.getText();
        String emailStr = emailTextField.getText();

        if (! emailStr.matches(EMAIL_REGEX)) {
            AlertUtils.alert(Alert.AlertType.WARNING, "注册失败", "邮箱格式错误");
            return;
        }

        boolean isSuccess = userService.register(passwordStr, emailStr);
        if (isSuccess) {
            toLogin();
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "注册失败", "系统错误");
        }
    }

    /**
     * 转到登录界面
     */
    private void toLogin() {
        registerView.close();
        ApplicationUtils.startAndShow(loginView);
    }
}
