package com.d.passwordmanager.views;

import com.d.passwordmanager.controller.EditPasswordController;
import com.d.passwordmanager.pojo.PasswordRecord;
import javafx.application.Application;
import com.d.passwordmanager.command.utils.ApplicationUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class EditPasswordView extends BaseView {

    private Stage stage;

    private EditPasswordController editPasswordController;
    public void setEditPasswordController(EditPasswordController editPasswordController) {
        this.editPasswordController = editPasswordController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage stage, PasswordRecord oldPasswordRecord) throws IOException {
        editPasswordController.setOldPasswordRecord(oldPasswordRecord);
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;

        FXMLLoader fxmlLoader = ApplicationUtils.load("editPassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("编辑");
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void close() {
        ApplicationUtils.close(stage);
    }
}