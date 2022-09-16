package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginView extends BaseView {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = ApplicationUtils.load("login.fxml");
        Parent parent = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        Scene scene = new Scene(parent, loginController.getPrefWidth(), loginController.getPrefHeight());
        stage.setTitle("登录");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void close() {
        ApplicationUtils.close(stage);
    }
}
