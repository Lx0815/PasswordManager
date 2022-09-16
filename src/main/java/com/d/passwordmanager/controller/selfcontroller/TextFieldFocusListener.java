package com.d.passwordmanager.controller.selfcontroller;

/**
 * @author: Ding
 * @date: 2022/9/15 14:48
 * @description:
 * @modify:
 */


import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

/**
 * Created by loongshawn on 2016/11/17.
 *
 * NOTE 密码框监听
 */
public class TextFieldFocusListener implements ChangeListener<Boolean> {

    private TextField textField;
    private MyStyleTextFieldOfPassWord myStyleTextFieldOfPassWord;

    public TextFieldFocusListener(TextField textField,MyStyleTextFieldOfPassWord myStyleTextFieldOfPassWord){
        this.textField = textField;
        this.myStyleTextFieldOfPassWord = myStyleTextFieldOfPassWord;
    }

    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {

        if (newValue){
            String password = myStyleTextFieldOfPassWord.getPassword();
            if (password != null) {
                textField.setText(password);
            }
        } else {
            String echo = getEcho(myStyleTextFieldOfPassWord.getPassword());
            myStyleTextFieldOfPassWord.setEcho(echo);
            textField.setText(echo);
        }
    }

    public static String getEcho(String password){

        String echo = "";

        if (password != null && !password.equals("")){

            int length = password.length();

            echo = "*".repeat(length);
        }

        return echo;
    }
}
