package com.d.passwordmanager.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.pojo.User;
import com.d.passwordmanager.service.UserService;
import com.d.passwordmanager.service.impl.UserServiceImpl;
import com.d.passwordmanager.views.LoginView;
import com.d.passwordmanager.views.RegisterView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.springframework.util.ObjectUtils;

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

    @FXML // fx:id="answer1TextField"
    private TextField answer1TextField; // Value injected by FXMLLoader

    @FXML // fx:id="answer2TextField"
    private TextField answer2TextField; // Value injected by FXMLLoader

    @FXML // fx:id="answer3TextField"
    private TextField answer3TextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="question1TextField"
    private TextField question1TextField; // Value injected by FXMLLoader

    @FXML // fx:id="question2TextField"
    private TextField question2TextField; // Value injected by FXMLLoader

    @FXML // fx:id="question3TextField"
    private TextField question3TextField; // Value injected by FXMLLoader

    @FXML // fx:id="registerButton"
    private Button registerButton; // Value injected by FXMLLoader

    @FXML // fx:id="registerPanel"
    private Pane registerPanel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert answer1TextField != null : "fx:id=\"answer1TextField\" was not injected: check your FXML file 'register.fxml'.";
        assert answer2TextField != null : "fx:id=\"answer2TextField\" was not injected: check your FXML file 'register.fxml'.";
        assert answer3TextField != null : "fx:id=\"answer3TextField\" was not injected: check your FXML file 'register.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'register.fxml'.";
        assert question1TextField != null : "fx:id=\"question1TextField\" was not injected: check your FXML file 'register.fxml'.";
        assert question2TextField != null : "fx:id=\"question2TextField\" was not injected: check your FXML file 'register.fxml'.";
        assert question3TextField != null : "fx:id=\"question3TextField\" was not injected: check your FXML file 'register.fxml'.";
        assert registerButton != null : "fx:id=\"registerButton\" was not injected: check your FXML file 'register.fxml'.";
        assert registerPanel != null : "fx:id=\"registerPanel\" was not injected: check your FXML file 'register.fxml'.";

    }


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

    /**
     * 进行注册操作
     *
     * @param mouseEvent
     */
    public void doRegister(MouseEvent mouseEvent) {
        User user = getUserByTextField();
        if (ObjectUtils.isEmpty(user)) return;

        boolean isSuccess = userService.register(user);
        if (isSuccess) {
            toLogin();
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "注册失败", "系统错误");
        }
    }

    /**
     * 从 TextField 中获取用户对象
     * @return 返回 null 则表示用户还有必填字段未输入
     */
    private User getUserByTextField() {
        String password = passwordTextField.getText();
        String question1 = question1TextField.getText();
        String question2 = question2TextField.getText();
        String question3 = question3TextField.getText();
        String answer1 = answer1TextField.getText();
        String answer2 = answer2TextField.getText();
        String answer3 = answer3TextField.getText();

        if (ObjectUtils.isEmpty(password) || ObjectUtils.isEmpty(question1) || ObjectUtils.isEmpty(answer1)) {
            AlertUtils.alert(Alert.AlertType.WARNING, "您还有必填项目为空");
            return null;
        }

        return new User(password, question1, answer1, question2, answer2, question3, answer3);
    }

    /**
     * 转到登录界面
     */
    private void toLogin() {
        registerView.close();
        ApplicationUtils.startAndShow(loginView);
    }
}
