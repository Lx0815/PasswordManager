package com.d.passwordmanager.controller;

import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.command.utils.PasswordUtils;
import com.d.passwordmanager.service.UserService;
import com.d.passwordmanager.views.FindPasswordView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import org.springframework.util.ObjectUtils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class FindPasswordController {

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

    @FXML // fx:id="preQuestion1Label"
    private Label preQuestion1Label; // Value injected by FXMLLoader

    @FXML // fx:id="preQuestion2Label"
    private Label preQuestion2Label; // Value injected by FXMLLoader

    @FXML // fx:id="preQuestion3Label"
    private Label preQuestion3Label; // Value injected by FXMLLoader

    @FXML // fx:id="question1Label"
    private Label question1Label; // Value injected by FXMLLoader

    @FXML // fx:id="question2Label"
    private Label question2Label; // Value injected by FXMLLoader

    @FXML // fx:id="question3Label"
    private Label question3Label; // Value injected by FXMLLoader


    /* Spring */
    private UserService userService;
    private FindPasswordView findPasswordView;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setFindPasswordView(FindPasswordView findPasswordView) {
        this.findPasswordView = findPasswordView;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert answer1TextField != null : "fx:id=\"answer1TextField\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert answer2TextField != null : "fx:id=\"answer2TextField\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert answer3TextField != null : "fx:id=\"answer3TextField\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert preQuestion1Label != null : "fx:id=\"preQuestion1Label\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert preQuestion2Label != null : "fx:id=\"preQuestion2Label\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert preQuestion3Label != null : "fx:id=\"preQuestion3Label\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert question1Label != null : "fx:id=\"question1Label\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert question2Label != null : "fx:id=\"question2Label\" was not injected: check your FXML file 'findPassword.fxml'.";
        assert question3Label != null : "fx:id=\"question3Label\" was not injected: check your FXML file 'findPassword.fxml'.";

    }

    /**
     * 提交修改密码
     *
     * @param event
     */
    @FXML
    void commitEdit(MouseEvent event) {
        String answer1 = answer1TextField.getText();
        String answer2 = question2Label.isVisible() ? answer2TextField.getText() : null;
        String answer3 = question3Label.isVisible() ? answer3TextField.getText() : null;

        boolean flag = userService.checkAnswer(answer1, answer2, answer3);
        if (flag) {
            String password = passwordTextField.getText();
            if (!PasswordUtils.checkPassword(password)) {
                PasswordUtils.alertPasswordError();
            }
            boolean isSuccess = userService.updatePassword(password);
            if (isSuccess) {
                findPasswordView.close();
                AlertUtils.alert(Alert.AlertType.INFORMATION, "密码修改成功");
            } else {
                AlertUtils.alert(Alert.AlertType.WARNING, "密码修改失败");
            }
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "请输入问题的答案");
        }
    }


    /**
     * 初始化密保问题及其相关控件
     */
    public void initView() {
        List<String> questionList = userService.selectQuestions();
        String question1 = questionList.get(0);
        String question2 = questionList.get(1);
        String question3 = questionList.get(2);

        if (ObjectUtils.isEmpty(question1)) {
            throw new RuntimeException("至少应有一个密保问题，可是查询到 0 个");
        } else {
            question1Label.setText(question1);
        }

        if (ObjectUtils.isEmpty(question2)) {
            preQuestion2Label.setVisible(false);
            question2Label.setVisible(false);
            answer2TextField.setVisible(false);
        } else {
            question2Label.setText(question2);
        }

        if (ObjectUtils.isEmpty(question3)) {
            preQuestion3Label.setVisible(false);
            question3Label.setVisible(false);
            answer3TextField.setVisible(false);
        } else {
            question3Label.setText(question3);
        }
    }

}