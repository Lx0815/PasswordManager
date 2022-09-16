package com.d.passwordmanager.controller;

import com.d.passwordmanager.views.MessageBoxView;

import javafx.fxml.FXML;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

/**
 * @author: Ding
 * @date: 2022/8/20 23:31
 * @description:
 * @modify:
 */

@Deprecated
public class MessageBoxController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="cancelButton"
    private Button cancelButton; // Value injected by FXMLLoader

    @FXML // fx:id="messageBoxPanel"
    private BorderPane messageBoxPanel; // Value injected by FXMLLoader

    @FXML // fx:id="messageTextArea"
    private TextArea messageTextArea; // Value injected by FXMLLoader

    @FXML // fx:id="messageTypeLabel"
    private Label messageTypeLabel; // Value injected by FXMLLoader

    /* Spring */
    private MessageBoxView messageBoxView;

    public void show() {
        messageBoxView.show();
    }

    public void close() {
        messageBoxView.close();
    }

    @FXML
    private void doCancel(MouseEvent event) {
        close();
    }

    public void setMessageBoxView(MessageBoxView messageBoxView) {
        this.messageBoxView = messageBoxView;
    }

    public ResourceBundle getResources() {
        return resources;
    }

    public URL getLocation() {
        return location;
    }

    public Button getCancelButton() {
        return cancelButton;
    }

    public BorderPane getMessageBoxPanel() {
        return messageBoxPanel;
    }

    public TextArea getMessageTextArea() {
        return messageTextArea;
    }

    public Label getMessageTypeLabel() {
        return messageTypeLabel;
    }

    public MessageBoxView getMessageBoxView() {
        return messageBoxView;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'messageBox.fxml'.";
        assert messageBoxPanel != null : "fx:id=\"messageBoxPanel\" was not injected: check your FXML file 'messageBox.fxml'.";
        assert messageTextArea != null : "fx:id=\"messageTextArea\" was not injected: check your FXML file 'messageBox.fxml'.";
        assert messageTypeLabel != null : "fx:id=\"messageTypeLabel\" was not injected: check your FXML file 'messageBox.fxml'.";
    }

    public void showMessage(String messageType, String message) {
        messageBoxView.start(new Stage());
        messageTypeLabel.setText(messageType);
        messageTextArea.setText(message);
        show();
    }
}
