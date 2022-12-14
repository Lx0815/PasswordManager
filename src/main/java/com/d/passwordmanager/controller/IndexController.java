package com.d.passwordmanager.controller;

import com.d.passwordmanager.command.constant.PasswordStrength;
import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.command.utils.ApplicationUtils;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.service.PasswordService;
import com.d.passwordmanager.views.CreatePasswordView;
import com.d.passwordmanager.views.EditPasswordView;
import com.d.passwordmanager.views.ImportPasswordView;
import com.d.passwordmanager.views.IndexView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.util.Callback;
import org.springframework.util.ObjectUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import static com.d.passwordmanager.command.utils.PasswordUtils.getPasswordRecordByTextFields;

/**
 * @author: Ding
 * @date: 2022/8/25 8:32
 * @description: indexView 对应的控制器
 * @modify:
 */


public class IndexController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="addOneButton"
    private Button addOneButton; // Value injected by FXMLLoader

    @FXML // fx:id="contentTableView"
    private TableView<PasswordRecord> contentTableView; // Value injected by FXMLLoader

    @FXML // fx:id="checkboxColumn"
    private TableColumn<PasswordRecord, CheckBox> checkboxColumn; // Value injected by FXMLLoader

    @FXML // fx:id="domainNameColumn"
    private TableColumn<PasswordRecord, String> domainNameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionColumn"
    private TableColumn<PasswordRecord, String> descriptionColumn; // Value injected by FXMLLoader

    @FXML // fx:id="username"
    private TableColumn<PasswordRecord, String> usernameColumn; // Value injected by FXMLLoader

    @FXML // fx:id="passwordColumn"
    private TableColumn<PasswordRecord, String> passwordColumn; // Value injected by FXMLLoader

    @FXML // fx:id="copyColumn"
    private TableColumn<PasswordRecord, PasswordStrength> passwordStrengthColumn; // Value injected by FXMLLoader

    @FXML // fx:id="createPasswordButton"
    private Button createPasswordButton; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTextField"
    private TextField descriptionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="domainNameTextField"
    private TextField domainNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="usernameTextField"
    private TextField usernameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="passwordTextField"
    private PasswordField passwordTextField; // Value injected by FXMLLoader

    @FXML // fx:id="indexPanel"
    private BorderPane indexPanel; // Value injected by FXMLLoader

    @FXML // fx:id="removeButton"
    private Button removeButton; // Value injected by FXMLLoader

    @FXML // fx:id="searchButton"
    private Button searchButton; // Value injected by FXMLLoader

    @FXML // fx:id="searchTextField"
    private TextField searchTextField; // Value injected by FXMLLoader

    @FXML // fx:id="totalPasswordTipsLabel"
    private Label totalPasswordTipsLabel; // Value injected by FXMLLoader

    @FXML // fx:id="tablePanel"
    private Pane tablePanel; // Value injected by FXMLLoader


    /* Spring */

    private PasswordService passwordService;
    private CreatePasswordView createPasswordView;
    private EditPasswordView editPasswordView;
    private ImportPasswordView importPasswordView;
    private IndexView indexView;


    /* Others */
    private int passwordRecordSize;
    private String searchKeyword;
    private List<PasswordRecord> passwordRecordList;

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public void setCreatePasswordView(CreatePasswordView createPasswordView) {
        this.createPasswordView = createPasswordView;
    }

    public void setEditPasswordView(EditPasswordView editPasswordView) {
        this.editPasswordView = editPasswordView;
    }

    public void setImportPasswordView(ImportPasswordView importPasswordView) {
        this.importPasswordView = importPasswordView;
    }

    public void setIndexView(IndexView indexView) {
        this.indexView = indexView;
    }

    @FXML
        // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert addOneButton != null : "fx:id=\"addOneButton\" was not injected: check your FXML file 'index.fxml'.";
        assert checkboxColumn != null : "fx:id=\"checkboxColumn\" was not injected: check your FXML file 'index.fxml'.";
        assert contentTableView != null : "fx:id=\"contentTableView\" was not injected: check your FXML file 'index.fxml'.";
        assert passwordStrengthColumn != null : "fx:id=\"copyColumn\" was not injected: check your FXML file 'index.fxml'.";
        assert createPasswordButton != null : "fx:id=\"createPasswordButton\" was not injected: check your FXML file 'index.fxml'.";
        assert descriptionColumn != null : "fx:id=\"descriptionColumn\" was not injected: check your FXML file 'index.fxml'.";
        assert descriptionTextField != null : "fx:id=\"descriptionTextField\" was not injected: check your FXML file 'index.fxml'.";
        assert domainNameColumn != null : "fx:id=\"domainNameColumn\" was not injected: check your FXML file 'index.fxml'.";
        assert domainNameTextField != null : "fx:id=\"domainNameTextField\" was not injected: check your FXML file 'index.fxml'.";
        assert indexPanel != null : "fx:id=\"indexPanel\" was not injected: check your FXML file 'index.fxml'.";
        assert passwordColumn != null : "fx:id=\"passwordColumn\" was not injected: check your FXML file 'index.fxml'.";
        assert passwordTextField != null : "fx:id=\"passwordTextField\" was not injected: check your FXML file 'index.fxml'.";
        assert removeButton != null : "fx:id=\"removeButton\" was not injected: check your FXML file 'index.fxml'.";
        assert searchButton != null : "fx:id=\"searchButton\" was not injected: check your FXML file 'index.fxml'.";
        assert searchTextField != null : "fx:id=\"searchTextField\" was not injected: check your FXML file 'index.fxml'.";
        assert totalPasswordTipsLabel != null : "fx:id=\"totalPasswordTipsLabel\" was not injected: check your FXML file 'index.fxml'.";
    }


    /**
     * 初始化界面相关内容
     */
    public void initView() {
        initColumn();

        initOthers();
    }

    private void initOthers() {
        totalPasswordTipsLabel.setText("共 " + passwordRecordSize + " 条密码记录");
    }

    private void initColumn() {
        // 通过筛选关键词获取密码记录
        passwordRecordList = passwordService.selectByKeyword(searchKeyword);

        passwordRecordSize = passwordRecordList.size();

        contentTableView.setEditable(true);

        checkboxColumn.setStyle("-fx-alignment: center;");

        // 设置单元格值工厂
        checkboxColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        domainNameColumn.setCellValueFactory(new PropertyValueFactory<>("domainName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passwordEcho"));
        passwordStrengthColumn.setCellValueFactory(new PropertyValueFactory<>("passwordStrength"));

        // 设置排序时比较规则
        checkboxColumn.setComparator((o1, o2) -> {
            // o1 true  o2 false
            if (o1.isSelected() && !o2.isSelected()) {
                return 1;
            }
            // o1 false  02 true
            if (!o1.isSelected() && o2.isSelected()) {
                return -1;
            }
            return 0;
        });
        domainNameColumn.setComparator((Comparator.naturalOrder()));
        descriptionColumn.setComparator(Comparator.naturalOrder());
        usernameColumn.setComparator(Comparator.naturalOrder());
        passwordStrengthColumn.setComparator(Comparator.naturalOrder());

        // 设置点击事件
        contentTableView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                PasswordRecord selectedItem = contentTableView.getSelectionModel().getSelectedItem();
                // 防止点击到的位置没有项导致 NPE
                if (ObjectUtils.isEmpty(selectedItem)) return;
                ApplicationUtils.startAndShow(editPasswordView, selectedItem);
            }
        });

        // 给弱密码添加强调背景色
        passwordStrengthColumn.setCellFactory(new Callback<>() {
            @Override
            public TableCell<PasswordRecord, PasswordStrength> call(TableColumn<PasswordRecord, PasswordStrength> param) {
                return new TableCell<>() {
                    @Override
                    protected void updateItem(PasswordStrength item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                            setGraphic(null);
                        } else {
                            setText(item.name());
                            if (item == PasswordStrength.WEAK) {
                                this.setStyle("-fx-text-fill: #ff0000");
                            } else if (item == PasswordStrength.MEDIUM) {
                                this.setStyle("-fx-text-fill: #ffa500");
                            } else if (item == PasswordStrength.STRENGTH) {
                                this.setStyle("-fx-text-fill: #3bff00");
                            }
                        }
                    }
                };
            }
        });

        contentTableView.setItems(FXCollections.observableList(passwordRecordList));
    }

    /**
     * 删除选择的密码，当 删除按钮 被点击时调用
     *
     * @param mouseEvent 鼠标事件
     */
    @FXML
    public void deleteSelected(MouseEvent mouseEvent) {
        Optional<ButtonType> buttonType = AlertUtils.alert(Alert.AlertType.CONFIRMATION, "确认删除吗？");
        // 选择了 取消 则不进行删除
        if (!ObjectUtils.nullSafeEquals(buttonType.get(), ButtonType.OK)) return;

        // 获取所有项
        ObservableList<PasswordRecord> items = checkboxColumn.getTableView().getItems();
        // 要删除的项
        List<PasswordRecord> deleteList = new LinkedList<>();
        // 填充要删除的项
        items.stream().filter(it -> it.getSelected().isSelected()).forEach(deleteList::add);
        // 若没有要删除的则直接返回
        if (ObjectUtils.isEmpty(deleteList)) return;

        boolean isSuccess = passwordService.deleteByList(deleteList);
        refresh(isSuccess);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "删除成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "删除失败");
        }
    }

    /**
     * 增加一条密码记录
     *
     * @param event 鼠标事件
     */
    @FXML
    void addOnePasswordRecord(MouseEvent event) {
        PasswordRecord passwordRecord = getPasswordRecordByTextFields(
                domainNameTextField, descriptionTextField, usernameTextField, passwordTextField
        );
        if (ObjectUtils.isEmpty(passwordRecord)) return;

        boolean isSuccess = passwordService.insertOne(passwordRecord);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "添加成功");
            refresh();
            removeRecordFromTextField();
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "删除失败");
        }
    }

    /**
     * 当搜索按钮被点击
     *
     * @param event
     */
    @FXML
    void doSearch(MouseEvent event) {
        String keyword = getSearchKeyWord();
        if (ObjectUtils.isEmpty(keyword)) return;

        passwordRecordList = passwordService.selectByKeyword(keyword);
        searchKeyword = keyword;
        refresh();
    }

    /**
     * 导出密码为 CSV 格式
     *
     * @param event 鼠标事件
     */
    @FXML
    void exportFormCSV(MouseEvent event) throws FileNotFoundException {
        File file = indexView.showSaveWindow();
        if (ObjectUtils.isEmpty(file)) return;
        try {
            if (!file.exists() && !file.createNewFile())
                AlertUtils.alert(Alert.AlertType.WARNING, "导出失败，请重新选择位置");
            boolean isSuccess = passwordService.savaToFile(file, passwordService);
            if (isSuccess) {
                AlertUtils.alert(Alert.AlertType.INFORMATION, "导出成功");
            } else {
                AlertUtils.alert(Alert.AlertType.WARNING, "导出失败");
            }
        } catch (IOException e) {
            AlertUtils.alert(Alert.AlertType.WARNING, "导出失败，请重新选择位置");
        }
    }

    /**
     * 从 CSV 格式的文件导入密码
     *
     * @param event 鼠标事件
     */
    @FXML
    void importFromCSV(MouseEvent event) {
        ApplicationUtils.startAndShow(importPasswordView);
    }

    /**
     * 移除搜索框的内容
     *
     * @param event
     */
    @FXML
    void removeSearchTextField(MouseEvent event) {
        if (!ObjectUtils.isEmpty(searchKeyword)) {
            searchKeyword = null;
            searchTextField.setText(null);
            refresh();
        }
    }

    /**
     * 创建密码，即打开 createPasswordView
     *
     * @param mouseEvent
     */
    @FXML
    public void createPassword(MouseEvent mouseEvent) {
        ApplicationUtils.startAndShow(createPasswordView);
    }

    /**
     * 获取搜索框中的关键字
     *
     * @return
     */
    private String getSearchKeyWord() {
        return searchTextField.getText();
    }

    /**
     * 移除四个搜索框中的内容
     */
    private void removeRecordFromTextField() {
        domainNameTextField.setText(null);
        descriptionTextField.setText(null);
        usernameTextField.setText(null);
        passwordTextField.setText(null);
    }

    /**
     * 刷新
     */
    public void refresh() {
        refresh(true);
    }

    /**
     * 如果 flag 为 true 则刷新
     *
     * @param flag 是否刷新
     */
    public void refresh(boolean flag) {
        if (flag) {
            initView();
        }
    }
}
