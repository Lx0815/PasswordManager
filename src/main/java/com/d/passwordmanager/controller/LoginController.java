package com.d.passwordmanager.views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

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

    @FXML // fx:id="passwordTipsLabel"
    private Label passwordTipsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="titleLabel"
    private Label titleLabel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert loginButton != null : "fx:id=\"loginButton\" was not injected: check your FXML file 'login.fxml'.";
        assert loginPanel != null : "fx:id=\"loginPanel\" was not injected: check your FXML file 'login.fxml'.";
        assert passwordTipsLabel != null : "fx:id=\"passwordTipsLabel\" was not injected: check your FXML file 'login.fxml'.";
        assert titleLabel != null : "fx:id=\"titleLabel\" was not injected: check your FXML file 'login.fxml'.";

    }

    @FXML
    public double getPrefHeight() {
        return loginPanel.getPrefHeight();
    }

    @FXML
    public double getPrefWidth() {
        return loginPanel.getPrefWidth();
    }

}
