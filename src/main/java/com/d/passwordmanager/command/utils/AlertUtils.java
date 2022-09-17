package com.d.passwordmanager.command.utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Modality;

import java.util.Optional;

/**
 * @author: Ding
 * @date: 2022/8/24 8:02
 * @description: 弹出框工具类
 * @modify:
 */


public class AlertUtils {

    private AlertUtils() {}

    /**
     * 弹出一个提示框
     * 相当于调用 alert(messageType, "", message);
     *
     * @param messageType 提示框类型
     * @param message 消息内容
     * @return 返回用户点击的按钮类型
     */
    public static Optional<ButtonType> alert(Alert.AlertType messageType, String message) {
        return alert(messageType, "", message);
    }

    /**
     * 弹出一个提示框
     * 相当于调用 alert(title, message, true);
     *
     * @param title 标题
     * @param message 消息内容
     * @return 返回用户点击的按钮类型
     */
    public static Optional<ButtonType> alert(String title, String message) {
        return alert(title, message, true);
    }

    /**
     * 弹出一个提示框
     * 相当于调用 alert(messageType, title, message, true);
     *
     * @param messageType 提示框类型
     * @param title 标题
     * @param message 消息内容
     * @return 返回用户点击的按钮类型
     */
    public static Optional<ButtonType> alert(Alert.AlertType messageType, String title, String message) {
        return alert(messageType, title, message, true);
    }

    /**
     * 弹出一个提示框
     * 相当于调用 alert(Alert.AlertType.INFORMATION, title, message, isModal);
     *
     * @param title 标题
     * @param message 消息内容
     * @param isModal 是否为模态
     * @return 返回用户点击的按钮类型
     */
    public static Optional<ButtonType> alert(String title, String message, boolean isModal) {
        return alert(Alert.AlertType.INFORMATION, title, message, isModal);
    }

    /**
     * 弹出一个提示框
     *
     * @param messageType 提示框类型
     * @param title 标题
     * @param message 消息内容
     * @param isModal 是否为模态
     * @return 返回用户点击的按钮类型
     */
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
