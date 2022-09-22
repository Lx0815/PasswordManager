package com.d.passwordmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * @author: Ding
 * @date: 2022/9/17 13:54
 * @description: showCreatePasswordView 对应的控制器
 * @modify:
 */


public class ShowCreatedPasswordController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="messageTextField"
    private TextField messageTextField; // Value injected by FXMLLoader

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert messageTextField != null : "fx:id=\"messageTextField\" was not injected: check your FXML file 'showCreatedPassword.fxml'.";

        messageTextField.setEditable(false);
    }

    /**
     * 初始化界面，根据传入的 password 初始化对应的内容
     *
     * @param password 生成的密码
     */
    public void initView(String password) {
        messageTextField.setText("生成的密码是：" + password);
    }
}
