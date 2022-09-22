package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.IndexController;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;

public class IndexView extends BaseView {

    private IndexController indexController;

    public static void main(String[] args) {
        launch(args);
    }

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        FXMLLoader fxmlLoader = ApplicationUtils.load("index.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("密码管理器 V1.0");

        indexController.initView();

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });

        stage.setScene(scene);
        stage.show();
    }

    public File showSaveWindow() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("导出");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("CSV Files", "*.csv")
        );
        fileChooser.initialFileNameProperty().set("password.csv");
        return fileChooser.showSaveDialog(stage);
    }
}