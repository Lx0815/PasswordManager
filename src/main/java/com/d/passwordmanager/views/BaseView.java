package com.d.passwordmanager.views;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author: Ding
 * @date: 2022/8/25 16:03
 * @description: 该类定义了基本界面应有的方法
 * @modify:
 */


public abstract class BaseView extends Application implements View {

    @Override
    public void start(Stage stage, Object[] params) throws IOException {

    }

    @Override
    public void start(Stage stage) throws IOException {
        throw new RuntimeException("BaseView 子类未重写 start 方法");
    }

    @Override
    public abstract void close();
}
