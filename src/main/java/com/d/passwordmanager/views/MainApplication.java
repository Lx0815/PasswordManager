package com.d.passwordmanager.views;

import com.d.passwordmanager.controller.LoginController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;


public class MainApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("login.fxml"));
        fxmlLoader.setControllerFactory(context::getBean);
        // 获取控制器之前必须先执行一次 load()
        Parent parent = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        Scene scene = new Scene(parent, loginController.getPrefWidth(), loginController.getPrefHeight());
        stage.setTitle("登录");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}