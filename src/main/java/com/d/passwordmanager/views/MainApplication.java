package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.service.UserService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

public class MainApplication extends Application {

    private static BaseView firstView;


    public static void main(String[] args) {
        ApplicationContext context = ApplicationUtils.context;
        UserService userService = context.getBean(UserService.class);
        Integer userCount = userService.selectCount();
        if (ObjectUtils.nullSafeEquals(userCount, 1)) {
            firstView = context.getBean(LoginView.class);
        } else {
            firstView = context.getBean(RegisterView.class);
        }

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // 在 JavaFX 应用程序的线程里启动程序
        ApplicationUtils.startAndShow(firstView);
    }
}