package com.d.passwordmanager.command.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;

/**
 * @author: Ding
 * @date: 2022/8/24 8:02
 * @description:
 * @modify:
 */


public class AlertUtils {

    private AlertUtils() {}

    public static Optional<ButtonType> alert(Alert.AlertType messageType, String message) {
        return alert(messageType, "", message);
    }

    public static Optional<ButtonType> alert(String title, String message) {
        return alert(title, message, true);
    }

    public static Optional<ButtonType> alert(Alert.AlertType messageType, String title, String message) {
        return alert(messageType, title, message, true);
    }

    public static Optional<ButtonType> alert(String title, String message, boolean isModal) {
        return alert(Alert.AlertType.INFORMATION, title, message, isModal);
    }

    public static Optional<ButtonType> alert(Alert.AlertType messageType, String title, String message, boolean isModal) {
        Alert alert = new Alert(messageType, message);
        alert.setTitle(title);
        if (isModal) {
            return alert.showAndWait();
        } else {
            alert.show();
            return Optional.empty();
        }
    }
}
