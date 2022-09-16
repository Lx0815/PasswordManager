package com.d.passwordmanager.views;
import javafx.application.Application;
import com.d.passwordmanager.command.utils.ApplicationUtils;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterView extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        FXMLLoader fxmlLoader = ApplicationUtils.load("");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("");
        stage.setScene(scene);
    }
    
    public void show() {
        stage.show();
    }

    public void close() {
        stage.close();
    }
}