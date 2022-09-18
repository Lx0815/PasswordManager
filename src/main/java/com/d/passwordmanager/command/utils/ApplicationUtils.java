package com.d.passwordmanager.command.utils;

import com.d.passwordmanager.views.BaseView;
import com.d.passwordmanager.views.LoginView;
import com.d.passwordmanager.views.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author: Ding
 * @date: 2022/8/22 20:58
 * @description: 应用程序工具类
 * @modify:
 */


public class ApplicationUtils {

    /**
     * 整个 Spring 应用程序上下文对象
     */
    public static ApplicationContext context;

    /*
      静态加载 Spring 上下文
     */
    static {
        context = new ClassPathXmlApplicationContext("application-context.xml");
    }

    private ApplicationUtils() {}

    /**
     * 根据输入的 fxml 配置文件加载得到其 FXMLLoader 对象，其主要作用是将 FXML 对象中的 Controller 替换为 Spring 容器中的 Controller
     *
     * @param resource fxml 文件所在的路径，可以为相对路径
     * @return 返回已配置好的 FXMLLoader 对象
     * @throws IOException
     */
    public static FXMLLoader load(String resource) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(resource));
        fxmlLoader.setControllerFactory(context::getBean);
        return fxmlLoader;
    }

    /**
     * 进一步封装对 应用程序的 start() ，并统一的处理异常
     *
     * @param view 要打开的页面
     */
    public static void startAndShow(BaseView view, Object... params) {
        try {
            if (params.length != 0) {
                view.start(new Stage(), params);
            } else {
                view.start(new Stage());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
