package com.d.passwordmanager.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;

/**
 * @author: Ding
 * @date: 2022/9/20 17:17
 * @description:
 * @modify:
 */


public class ImportPasswordController {

    @FXML
    private Pane importPasswordPanel;

    @FXML
    private void initialize() {
        FileChooser chooser = new FileChooser();
//        importPasswordPanel.getChildren().add(chooser);
    }

}
