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
 */
public class TextFieldTextListener implements ChangeListener<String> {

    private TextField textField;
    private MyStyleTextFieldOfPassWord myStyleTextFieldOfPassWord;

    public TextFieldTextListener(TextField textField,MyStyleTextFieldOfPassWord myStyleTextFieldOfPassWord){
        this.textField = textField;
        this.myStyleTextFieldOfPassWord = myStyleTextFieldOfPassWord;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!textField.getText().equals(myStyleTextFieldOfPassWord.getEcho())){
            myStyleTextFieldOfPassWord.setPassword(newValue);
        }
    }
}