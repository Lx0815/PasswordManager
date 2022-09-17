package com.d.passwordmanager.controller;

import java.net.URL;
import java.util.*;

import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.views.ShowCreatePasswordView;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author: Ding
 * @date: 2022/9/15 18:08:49
 * @description: CreatePasswordView 对应的控制器
 * @modify:
 */


public class CreatePasswordController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="digitsFlowPanel"
    private FlowPane digitsFlowPanel; // Value injected by FXMLLoader

    @FXML // fx:id="includeDigit"
    private CheckBox digitsCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="includeLowerCaseLetters"
    private CheckBox lowerCaseCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="includeUppercaseLetter"
    private CheckBox upperCaseCheckBox; // Value injected by FXMLLoader

    @FXML // fx:id="includeSymbols"
    private CheckBox symbolsCheckBox; // Value injected by FXMLLoader

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


    /* Spring */

    private ShowCreatePasswordView showCreatePasswordView;
    public void setShowCreatePasswordView(ShowCreatePasswordView showCreatePasswordView) {
        this.showCreatePasswordView = showCreatePasswordView;
    }
    /* Others */

    /**
     * 存放所有符号
     */
    private List<CheckBox> symbolsList;

    /**
     * 存放所有数字
     */
    private List<CheckBox> digitsList;

    /**
     * 存放所有小写字母
     */
    private List<CheckBox> lowerCaseList;

    /**
     * 存放所有大写字母
     */
    private List<CheckBox> upperCaseList;


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert digitsFlowPanel != null : "fx:id=\"digitsFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert digitsList != null : "fx:id=\"includeDigit\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert lowerCaseCheckBox != null : "fx:id=\"includeLowerCaseLetters\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert upperCaseCheckBox != null : "fx:id=\"includeUppercaseLetter\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert lowerCaseFlowPanel != null : "fx:id=\"lowerCaseFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert maxPasswordLength != null : "fx:id=\"maxPasswordLength\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert minPasswordLength != null : "fx:id=\"minPasswordLength\" was not injected: check your FXML file 'createPassword.fxml'.";
        assert upperCaseFlowPanel != null : "fx:id=\"upperCaseFlowPanel\" was not injected: check your FXML file 'createPassword.fxml'.";

    }

    /**
     * 初始化界面
     */
    public void initView() {

        // 初始化面板样式
        initFlowPanelStyle(digitsFlowPanel, lowerCaseFlowPanel, upperCaseFlowPanel, symbolsFlowPanel);

        // 初始化容器
        symbolsList = Objects.requireNonNullElse(symbolsList, new ArrayList<>(32));
        digitsList = Objects.requireNonNullElse(digitsList, new ArrayList<>(10));
        lowerCaseList = Objects.requireNonNullElse(lowerCaseList, new ArrayList<>(26));
        upperCaseList = Objects.requireNonNullElse(upperCaseList, new ArrayList<>(26));

        // 初始化面板内容
        initFlowPanelContent(digitsFlowPanel, lowerCaseFlowPanel, upperCaseFlowPanel, symbolsFlowPanel);
    }

    /**
     * 初始化面板内容，此处主要 创建组件后根据 Unicode 字符进行分流，使其添加到不同的 Panel 中
     *
     * @param digitsFlowPanel 数字面板
     * @param lowerCaseFlowPanel 小写字母面板
     * @param upperCaseFlowPanel 大写字母容器
     * @param symbolsFlowPanel 符号容器
     */
    private void initFlowPanelContent(FlowPane digitsFlowPanel, FlowPane lowerCaseFlowPanel, FlowPane upperCaseFlowPanel, FlowPane symbolsFlowPanel) {
        for (char i = '!'; i <= '~'; i++) {
            CheckBox temp = new CheckBox(String.valueOf(i));

            if (i >= '0' && i <= '9') {
                process(digitsFlowPanel, temp, digitsList, digitsCheckBox);
            } else if (i >= 'A' && i <= 'Z') {
                process(upperCaseFlowPanel, temp, upperCaseList, upperCaseCheckBox);
            } else if (i >= 'a' && i <= 'z') {
                process(lowerCaseFlowPanel, temp, lowerCaseList, lowerCaseCheckBox);
            } else {
                process(symbolsFlowPanel, temp, symbolsList, symbolsCheckBox);
            }
        }
    }

    /**
     * 真正的 使其添加到对应的 Panel 中
     *
     * @param flowPane 准备好的 Panel
     * @param temp 要添加的 CheckBox 组件
     * @param list 组件对应的容器
     * @param xxxCheckBox 其对应的整合型 CheckBox，如 数字0 所在的 checkBox 将对应 {@link #digitsCheckBox}
     */
    private void process(FlowPane flowPane, CheckBox temp, List<CheckBox> list, CheckBox xxxCheckBox) {
        list.add(temp);
        flowPane.getChildren().add(temp);
        temp.setOnMouseClicked(event -> {
            if (list.stream().allMatch(CheckBox::isSelected)) {
                xxxCheckBox.setSelected(true);
            } else {
                xxxCheckBox.setSelected(false);
            };
        });
    }

    /**
     * 初始化面板样式
     * @param flowPanes
     */
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

    /**
     * 选择/反选 所有数字，当 {@link #digitsCheckBox} 被选中时调用
     *
     * @param event 鼠标点击事件
     */
    @FXML
    void selectAllDigits(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            digitsList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            digitsList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    /**
     * 选择/反选 所有小写字母，当 {@link #lowerCaseCheckBox} 被选中时调用
     *
     * @param event 鼠标点击事件
     */
    @FXML
    void selectAllLowerCaseLetters(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            lowerCaseList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            lowerCaseList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    /**
     * 选择/反选 所有大写字母，当 {@link #upperCaseCheckBox} 被选中时调用
     *
     * @param event 鼠标点击事件
     */
    @FXML
    void selectAllUpperCaseLetters(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            upperCaseList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            upperCaseList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    /**
     * 选择/反选 所有符号，当 {@link #symbolsCheckBox} 被选中时调用
     *
     * @param event 鼠标点击事件
     */
    @FXML
    void selectAllSymbols(MouseEvent event) {
        if (((CheckBox) event.getSource()).isSelected()) {
            symbolsList.forEach(checkBox -> checkBox.setSelected(true));
        } else {
            symbolsList.forEach(checkBox -> checkBox.setSelected(false));
        }
    }

    /**
     * 生成密码，当 生成 按钮被点击时调用
     *
     * @param mouseEvent 鼠标事件
     */
    @FXML
    public void doCreatePassword(MouseEvent mouseEvent) {
        char[] chs = getAllSelect();
        int minLen = Integer.parseInt(minPasswordLength.getText());
        int maxLen = Integer.parseInt(maxPasswordLength.getText());
        String password = RandomStringUtils.random(new Random().nextInt(maxLen - minLen + 1) + minLen, chs);
        ApplicationUtils.startAndShow(showCreatePasswordView, password);
    }

    /**
     * 获取所有已选中的字符
     *
     * @return
     */
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
