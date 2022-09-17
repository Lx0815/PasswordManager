package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.ShowCreatePasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowCreatePasswordView extends BaseView {

    private Stage stage;

    private String password;

    private ShowCreatePasswordController showCreatePasswordController;
    public void setShowCreatePasswordController(ShowCreatePasswordController showCreatePasswordController) {
        this.showCreatePasswordController = showCreatePasswordController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage, Object[] params) throws IOException {
        password = (String) params[0];
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = ApplicationUtils.load("showCreatePassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("密码生成器");
        showCreatePasswordController.initView(password);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void close() {
        stage.close();
    }
}