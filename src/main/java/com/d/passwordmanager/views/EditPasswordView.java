package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.EditPasswordController;
import com.d.passwordmanager.pojo.PasswordRecord;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.util.Assert;

import java.io.IOException;

public class EditPasswordView extends BaseView {

    private EditPasswordController editPasswordController;

    public static void main(String[] args) {
        launch(args);
    }

    public void setEditPasswordController(EditPasswordController editPasswordController) {
        this.editPasswordController = editPasswordController;
    }

    @Override
    public void start(Stage stage, Object[] object) throws IOException {
        Assert.notNull(object, "一个必要的参数为 null");
        Assert.notEmpty(object, "一个必要的参数为 null");
        PasswordRecord passwordRecord = (PasswordRecord) object[0];

        editPasswordController.setOldPasswordRecord(passwordRecord);
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        FXMLLoader fxmlLoader = ApplicationUtils.load("editPassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("编辑");
        stage.setScene(scene);
        stage.show();
    }
}