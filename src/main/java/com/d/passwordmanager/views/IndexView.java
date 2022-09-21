package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.controller.IndexController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class IndexView extends BaseView {

    private IndexController indexController;
    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        super.start(stage);
        FXMLLoader fxmlLoader = ApplicationUtils.load("index.fxml");
        Parent parent = fxmlLoader.load();
        Scene scene = new Scene(parent);
        stage.setTitle("密码管理器 V1.0");

        indexController.initView();

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