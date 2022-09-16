package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

@Deprecated
public class MessageBoxView extends Application {

    private Scene scene;

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        try {
            FXMLLoader fxmlLoader = ApplicationUtils.load("messageBox.fxml");
            scene = new Scene(fxmlLoader.load());
            stage.setScene(scene);
            stage.initModality(Modality.APPLICATION_MODAL);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void close() {
        ((Stage) this.scene.getWindow()).close();
    }

    public void show() {
        stage.show();
    }
}
