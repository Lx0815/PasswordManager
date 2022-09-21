package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ImportPasswordView extends BaseView {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        FXMLLoader fxmlLoader = ApplicationUtils.load("importPassword.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("导入密码");
        stage.setScene(scene);
        stage.show();
    }
}