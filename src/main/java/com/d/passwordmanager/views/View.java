package com.d.passwordmanager.views;


import javafx.stage.Stage;

import java.io.IOException;

/**
 * @author: Ding
 * @date: 2022/7/18 14:36
 * @description:
 * @modify:
 */


public interface View {

    void close();

    void start(Stage stage, Object[] params) throws IOException;
}
