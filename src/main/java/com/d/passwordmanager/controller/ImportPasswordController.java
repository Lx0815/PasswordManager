package com.d.passwordmanager.controller;

import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.service.PasswordService;
import com.d.passwordmanager.views.ImportPasswordView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.MouseEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * @author: Ding
 * @date: 2022/9/20 17:17
 * @description:
 * @modify:
 */


public class ImportPasswordController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="descriptionTextField"
    private TextField descriptionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="domainNameTextField"
    private TextField domainNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private TextField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="template"
    private ToggleGroup template; // Value injected by FXMLLoader


    /* Spring */

    private ImportPasswordView importPasswordView;
    private PasswordService passwordService;
    private IndexController indexController;



    /**
     * csv文件标题行与 {@link com.d.passwordmanager.pojo.PasswordRecord} 的映射
     *
     * {@code Map<id, Map<property, title>>}
     */
    private Map<String, Map<String, String>> mapperMap;

    public void setImportPasswordView(ImportPasswordView importPasswordView) {
        this.importPasswordView = importPasswordView;
    }

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }



    /* Others */

    public void setIndexController(IndexController indexController) {
        this.indexController = indexController;
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert descriptionTextField != null : "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'importPassword.fxml'.";
        assert domainNameTextField != null : "fx:id=\"domainNameTextField\" was not injected: check your FXML file 'importPassword.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'importPassword.fxml'.";
        assert usernameTextField != null : "fx:id=\"usernameTextField\" was not injected: check your FXML file 'importPassword.fxml'.";

        loadXml();
        changeTextField(this.mapperMap.get("edge"));
    }

    /**
     * 从 csv-mapper-config.xml 文件中读取 csv 和 {@link com.d.passwordmanager.pojo.PasswordRecord} 之间的映射关系
     */
    private void loadXml() {

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("csv-mapper-config.xml")) {

            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            NodeList configList = document.getElementsByTagName("config");

            mapperMap = new HashMap<>();
            for (int i = 0; i < configList.getLength(); i++) {
                Node configNode = configList.item(i);
                if (configNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element configElement = (Element) configNode;
                    // 获取 id
                    String id = configElement.getAttribute("id");

                    // 设置为 6 是为了让 长度为 4 的数据不会使其扩容
                    Map<String, String> map = new HashMap<>(6);
                    mapperMap.put(id, map);

                    NodeList mapperList = configElement.getChildNodes();
                    for (int j = 0; j < mapperList.getLength(); j++) {
                        Node mapperNode = mapperList.item(j);
                        if (mapperNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element mapperElement = (Element) mapperNode;
                            String value = mapperElement.getAttribute("value");

                            map.put(mapperElement.getTagName(), value);
                        }
                    }
                }
            }
        } catch (IOException | ParserConfigurationException | SAXException e) {
            throw new RuntimeException(e);
        }

    }

    @FXML
    void selectChrome(MouseEvent event) {
        changeTextField(this.mapperMap.get("chrome"));
    }

    @FXML
    void selectEdge(MouseEvent event) {
        changeTextField(this.mapperMap.get("edge"));
    }

    @FXML
    void selectFirefox(MouseEvent event) {
        changeTextField(this.mapperMap.get("firefox"));
    }

    /**
     * 当 选择文件 按钮被点击时调用
     *
     * @param event
     */
    @FXML
    void selectFile(MouseEvent event) {
        doSelect(Map.of("domainName", domainNameTextField.getText(),
                "description", descriptionTextField.getText(),
                "username", usernameTextField.getText(),
                "password", passwordTextField.getText()));
    }

    /**
     * 更改映射模板
     *
     * @param map
     */
    private void changeTextField(Map<String, String> map) {
        domainNameTextField.setText(map.get("domainName"));
        descriptionTextField.setText(map.get("description"));
        usernameTextField.setText(map.get("username"));
        passwordTextField.setText(map.get("password"));
    }


    /**
     * 选择文件并调用 Service 层方法将数据保存
     *
     * @param mapperMap csv 文件标题行与 {@link com.d.passwordmanager.pojo.PasswordRecord} 的字段的映射关系
     */
    private void doSelect(Map<String, String> mapperMap) {
        File file = importPasswordView.doSelectFile();
        mapperMap = reserveKeyValue(mapperMap);
        boolean isSuccess = passwordService.importByCsv(file, mapperMap);
        if (isSuccess) {
            indexController.refresh();
            importPasswordView.close();
            AlertUtils.alert(Alert.AlertType.INFORMATION, "密码导入成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "密码导入失败");
        }
    }

    /**
     * 反转 map 的键值，即将 键 更改为 值，值更改为 键
     *
     * @param mapperMap
     */
    private <T> Map<T, T> reserveKeyValue(Map<T, T> mapperMap) {
        Map<T, T> newMap = new HashMap<>();
        Set<Map.Entry<T, T>> entrySet = mapperMap.entrySet();
        entrySet.forEach(it -> newMap.put(it.getValue(), it.getKey()));
        return newMap;
    }
}
