package com.d.passwordmanager.command.utils;

import com.d.passwordmanager.views.BaseView;
import com.d.passwordmanager.views.MainApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Ding
 * @date: 2022/8/22 20:58
 * @description:
 * @modify:
 */


public class ApplicationUtils {

    public static ApplicationContext context;

    static {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    private ApplicationUtils() {}

    public static FXMLLoader load(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(resource));
        fxmlLoader.setControllerFactory(context::getBean);
        return fxmlLoader;
    }

    public static void startAndShow(BaseView view) {
        try {
            view.start(new Stage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void close(Stage stage) {
        stage.close();
    }
}
