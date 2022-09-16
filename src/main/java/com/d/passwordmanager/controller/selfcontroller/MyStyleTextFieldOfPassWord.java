package com.d.passwordmanager.controller.selfcontroller;

import javafx.scene.control.TextField;

/**
 * Created by loongshawn on 2016/11/16.
 *
 * NOTE 为了避免密码框显示异常，通过textfield代替
 */
public class MyStyleTextFieldOfPassWord {

    private String password;
    private String echo;

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return password;
    }

    public void setEcho(String echo){
        this.echo = echo;
    }

    public String getEcho(){
        return echo;
    }


    public TextField getPasswordField(){

        TextField textField = new TextField();

        textField.focusedProperty().addListener(new TextFieldFocusListener(textField,this));
        textField.textProperty().addListener(new TextFieldTextListener(textField,this));

        return textField;
    }
}
