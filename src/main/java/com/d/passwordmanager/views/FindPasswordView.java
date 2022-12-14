package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.FindPasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class FindPasswordView extends BaseView {

    private FindPasswordController findPasswordController;

    public static void main(String[] args) {
        launch(args);
    }

    public void setFindPasswordController(FindPasswordController findPasswordController) {
        this.findPasswordController = findPasswordController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        FXMLLoader fxmlLoader = ApplicationUtils.load("findPassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("找回密码");
        findPasswordController.initView();
        stage.setScene(scene);
        stage.show();
    }
}