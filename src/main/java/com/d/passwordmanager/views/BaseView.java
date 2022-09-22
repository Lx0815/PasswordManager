package com.d.passwordmanager.views;

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

    protected Stage stage;

    @Override
    public void start(Stage stage, Object[] params) throws IOException {
        start(stage);
    }

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
    }

    @Override
    public void close() {
        stage.close();
    }
}
