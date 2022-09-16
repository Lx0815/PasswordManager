/**
 * Sample Skeleton for 'createPassword.fxml' Controller Class
 */

package com.d.passwordmanager.controller;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.*;
import java.util.function.IntFunction;
import java.util.stream.Stream;

import com.d.passwordmanager.command.constant.PasswordStrength;
import com.d.passwordmanager.command.utils.AlertUtils;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.apache.commons.lang3.RandomStringUtils;

public class CreatePasswordController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="digitsFlowPanel"
    private FlowPane digitsFlowPanel; // Value injected by FXMLLoader

    @FXML // fx:id="includeDigit"
    private CheckBox includeDigit; // Value injected by FXMLLoader

    @FXML // fx:id="includeLowerCaseLetters"
    private CheckBox includeLowerCaseLetters; // Value injected by FXMLLoader

    @FXML // fx:id="includeUppercaseLetter"
    private CheckBox includeUppercaseLetter; // Value injected by FXMLLoader

    @FXML // fx:id="includeSymbols"
    private CheckBox includeSymbols; // Value injected by FXMLLoader

    @FXML // fx:id="lowerCaseFlowPanel"
    private FlowPane lowerCaseFlowPanel; // Value injected by FXMLLoader

    @FXML // fx:id="maxPasswordLength"
    private TextField maxPasswordLength; // Value injected by FXMLLoader

    @FXML // fx:id="minPasswordLength"
    private TextField minPasswordLength; // Value injected by FXMLLoader

    @FXML // fx:id="symbolsFlowPanel"
    private FlowPane symbolsFlowPanel; // Value injected by FXMLLoader

    @FXML // fx:id="upperCaseFlowPanel"
    private FlowPane upperCaseFlowPanel; // Value injected by FXMLLoader

    /* Others */
    private List<CheckBox> symbolsList;

    private List<CheckBox> digitsList;

    private List<CheckBox> lowerCaseList;

    private List<CheckBox> upperCaseList;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert digitsFlowPanel != null : "fx:id=\"digitsFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert includeDigit != null : "fx:id=\"includeDigit\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert includeLowerCaseLetters != null : "fx:id=\"includeLowerCaseLetters\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert includeUppercaseLetter != null : "fx:id=\"includeUppercaseLetter\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert lowerCaseFlowPanel != null : "fx:id=\"lowerCaseFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert maxPasswordLength != null : "fx:id=\"maxPasswordLength\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert minPasswordLength != null : "fx:id=\"minPasswordLength\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert upperCaseFlowPanel != null : "fx:id=\"upperCaseFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";

    }

    public void initView() {

        initFlowPanelStyle(digitsFlowPanel, lowerCaseFlowPanel, upperCaseFlowPanel, symbolsFlowPanel);

        symbolsList = Objects.requireNonNullElse(symbolsList, new ArrayList<>(32));
        digitsList = Objects.requireNonNullElse(digitsList, new ArrayList<>(10));
        lowerCaseList = Objects.requireNonNullElse(lowerCaseList, new ArrayList<>(26));
        upperCaseList = Objects.requireNonNullElse(upperCaseList, new ArrayList<>(26));

        initFlowPanelContent(digitsFlowPanel, lowerCaseFlowPanel, upperCaseFlowPanel, symbolsFlowPanel);
    }

    private void initFlowPanelContent(FlowPane digitsFlowPanel, FlowPane lowerCaseFlowPanel, FlowPane upperCaseFlowPanel, FlowPane symbolsFlowPanel) {
        for (char i = '!'; i <= '~'; i++) {
            CheckBox temp = new CheckBox(String.valueOf(i));

            if (i >= '0' && i <= '9') {
                process(digitsFlowPanel, temp, digitsList, includeDigit);
            } else if (i >= 'A' && i <= 'Z') {
                process(upperCaseFlowPanel, temp, upperCaseList, includeUppercaseLetter);
            } else if (i >= 'a' && i <= 'z') {
                process(lowerCaseFlowPanel, temp, lowerCaseList, includeLowerCaseLetters);
            } else {
                process(symbolsFlowPanel, temp, symbolsList, includeSymbols);
            }
        }
    }

    private void process(FlowPane digitsFlowPanel, CheckBox temp, List<CheckBox> list, CheckBox includeXxx) {
        list.add(temp);
        digitsFlowPanel.getChildren().add(temp);
        temp.setOnMouseClicked(event -> {
            if (list.stream().allMatch(CheckBox::isSelected)) {
                includeXxx.setSelected(true);
            } else {
                includeXxx.setSelected(false);
            };
        });
    }

    public void initFlowPanelStyle(FlowPane... flowPanes) {
        for (FlowPane flowPane : flowPanes) {
            //设置节点水平摆放
            flowPane.setOrientation(Orientation.HORIZONTAL);
            //设置面板边缘内侧上、右、下、左空白的距离
            flowPane.setPadding(new Insets(20, 20, 20, 20));
            //设置面板上节点之间的水平距离为8像素
            flowPane.setHgap(8);
            //设置面板上节点之间的垂直距离为5像素
            flowPane.setVgap(5);
        }
    }


    @FXML
    void selectAllDigits(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            digitsList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            digitsList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    @FXML
    void selectAllLowerCaseLetters(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            lowerCaseList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            lowerCaseList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    @FXML
    void selectAllUpperCaseLetters(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            upperCaseList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            upperCaseList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    @FXML
    void selectAllSymbols(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            symbolsList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            symbolsList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    @FXML
    public void doCreatePassword(MouseEvent mouseEvent) {
        char[] chs = getAllSelect();
//        AlertUtils.alert(Alert.AlertType.INFORMATION, Arrays.toString(chs));
        int minLen = Integer.parseInt(minPasswordLength.getText());
        int maxLen = Integer.parseInt(maxPasswordLength.getText());
        String password = RandomStringUtils.random(new Random().nextInt(maxLen - minLen + 1) + minLen, chs);
        AlertUtils.alert("生成密码成功", "您生成的密码是：" + password);

    }

    private char[] getAllSelect() {
        List<Character> characterList = new LinkedList<>();
        digitsList.stream().filter(CheckBox::isSelected).forEach(checkBox -> characterList.add(checkBox.getText().charAt(0)));
        lowerCaseList.stream().filter(CheckBox::isSelected).forEach(checkBox -> characterList.add(checkBox.getText().charAt(0)));
        upperCaseList.stream().filter(CheckBox::isSelected).forEach(checkBox -> characterList.add(checkBox.getText().charAt(0)));
        symbolsList.stream().filter(CheckBox::isSelected).forEach(checkBox -> characterList.add(checkBox.getText().charAt(0)));
        int len = characterList.size();
        char[] chs = new char[len];
        for (int i = 0; i < len; i++) {
            chs[i] = characterList.get(i);
        }
        return chs;
    }
}
