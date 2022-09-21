package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.ShowCreatedPasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowCreatedPasswordView extends BaseView {

    private String password;

    private ShowCreatedPasswordController showCreatedPasswordController;
    public void setShowCreatedPasswordController(ShowCreatedPasswordController showCreatedPasswordController) {
        this.showCreatedPasswordController = showCreatedPasswordController;
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
        super.start(stage);
        FXMLLoader fxmlLoader = ApplicationUtils.load("showCreatedPassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("密码生成器");
        showCreatedPasswordController.initView(password);
        stage.setScene(scene);
        stage.show();
    }
}