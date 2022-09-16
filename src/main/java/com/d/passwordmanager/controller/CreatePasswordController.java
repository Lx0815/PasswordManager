/**
 * Sample Skeleton for 'createPassword.fxml' Controller Class
 */

package com.d.passwordmanager.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class CreatePasswordController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="includeDigit"
    private CheckBox includeDigit; // Value injected by FXMLLoader

    @FXML // fx:id="includeLowerCaseLetters"
    private CheckBox includeLowerCaseLetters; // Value injected by FXMLLoader

    @FXML // fx:id="includeUppercaseLetter"
    private CheckBox includeUppercaseLetter; // Value injected by FXMLLoader

    @FXML // fx:id="maxPasswordLength"
    private TextField maxPasswordLength; // Value injected by FXMLLoader

    @FXML // fx:id="minPasswordLength"
    private TextField minPasswordLength; // Value injected by FXMLLoader

    @FXML // fx:id="symbols"
    private CheckBox symbols; // Value injected by FXMLLoader

    @FXML // fx:id="symbolsFlowPanel"
    private FlowPane symbolsFlowPanel; // Value injected by FXMLLoader

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert includeDigit != null : "fx:id=\"includeDigit\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert includeLowerCaseLetters != null : "fx:id=\"includeLowerCaseLetters\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert includeUppercaseLetter != null : "fx:id=\"includeUppercaseLetter\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert maxPasswordLength != null : "fx:id=\"maxPasswordLength\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert minPasswordLength != null : "fx:id=\"minPasswordLength\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert symbols != null : "fx:id=\"symbols\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert symbolsFlowPanel != null : "fx:id=\"symbolsFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";

    }

}
