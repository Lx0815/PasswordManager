package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.service.UserService;
import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.util.ObjectUtils;

public class MainApplication extends Application {

    /**
     * 第一个界面。
     * 根据是否注册用户来选择进入 loginView 还是 registerView
     */
    private static BaseView firstView;


    public static void main(String[] args) {
        ApplicationContext context = ApplicationUtils.context;
        UserService userService = context.getBean(UserService.class);
        Integer userCount = userService.selectUserCount();
        if (ObjectUtils.nullSafeEquals(userCount, 1)) {
            firstView = context.getBean(LoginView.class);
        } else {
            firstView = context.getBean(RegisterView.class);
        }

        // 此方法只能在 非JavaFX 的线程中调用
        launch(args);
    }

    /**
     * 启动应用程序
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // 在 JavaFX 应用程序的线程里启动程序
        ApplicationUtils.startAndShow(firstView);
    }
}