package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.CreatePasswordController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreatePasswordView extends BaseView {

    private Stage stage;

    private CreatePasswordController createPasswordController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        FXMLLoader fxmlLoader = ApplicationUtils.load("createPassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("生成密码");
        createPasswordController.initView();
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void close() {
        ApplicationUtils.close(stage);
    }

    public void setCreatePasswordController(CreatePasswordController createPasswordController) {
        this.createPasswordController = createPasswordController;
    }
}