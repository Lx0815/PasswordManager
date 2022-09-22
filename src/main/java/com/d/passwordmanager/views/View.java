package com.d.passwordmanager.views;


import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description: 本项目中所有界面的根接口
 * @modify:
 */


public interface View {

    /**
     * 关闭当前窗口
     */
    void close();

    /**
     * 重载 {@link javafx.application.Application#start(Stage)} 方法使其初始化窗口时可以附带一些必要的参数
     *
     * @param stage 此应用程序的主要阶段，可以在其上设置应用程序场景。如果需要，应用程序可以创建其他阶段，但它们不会是主要阶段。
     * @param params 附带的参数
     * @throws IOException
     */
    void start(Stage stage, Object[] params) throws IOException;
}
