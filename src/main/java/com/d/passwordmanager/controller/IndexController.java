package com.d.passwordmanager.controller;

import java.io.IOException;
import java.net.URL;
import java.util.*;

import com.d.passwordmanager.command.constant.PasswordStrength;
import com.d.passwordmanager.command.utils.AlertUtils;
import com.d.passwordmanager.controller.selfcontroller.MyStyleTextFieldOfPassWord;
import com.d.passwordmanager.pojo.PasswordRecord;
import com.d.passwordmanager.service.PasswordService;
import com.d.passwordmanager.views.CreatePasswordView;
import com.d.passwordmanager.views.IndexView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.util.ObjectUtils;

/**
 * @author: Ding
 * @date: 2022/8/25 8:32
 * @description:
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

    @FXML // fx:id="account"
    private TableColumn<PasswordRecord, String> accountColumn; // Value injected by FXMLLoader

    @FXML // fx:id="passwordColumn"
    private TableColumn<PasswordRecord, TextField> passwordColumn; // Value injected by FXMLLoader

    @FXML // fx:id="copyColumn"
    private TableColumn<PasswordRecord, PasswordStrength> passwordStrengthColumn; // Value injected by FXMLLoader

    @FXML // fx:id="createPasswordButton"
    private Button createPasswordButton; // Value injected by FXMLLoader

    @FXML // fx:id="descriptionTextField"
    private TextField descriptionTextField; // Value injected by FXMLLoader

    @FXML // fx:id="domainNameTextField"
    private TextField domainNameTextField; // Value injected by FXMLLoader

    @FXML // fx:id="accountTextField"
    private TextField accountTextField; // Value injected by FXMLLoader

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


    /* Spring */

    private PasswordService passwordService;

    private CreatePasswordView createPasswordView;

    private IndexView indexView;

    /* Others */
    private int passwordRecordSize;

    private String searchKeyword;

    private List<PasswordRecord> passwordRecordList;


    @FXML // This method is called by the FXMLLoader when initialization is complete
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

    public void initIndex() {
        initColumn();

        initOthers();
    }

    private void initOthers() {
        totalPasswordTipsLabel.setText("共 " + passwordRecordSize + " 条密码记录");
    }

    private void initColumn() {
        passwordRecordList = passwordService.selectByKeyword(searchKeyword);

        passwordRecordSize = passwordRecordList.size();

        contentTableView.setEditable(true);

        // 设置单元格值工厂
        checkboxColumn.setCellValueFactory(new PropertyValueFactory<>("selected"));
        checkboxColumn.setStyle("-fx-alignment: center;");
        domainNameColumn.setCellValueFactory(new PropertyValueFactory<>("domainName"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        accountColumn.setCellValueFactory(new PropertyValueFactory<>("account"));
        passwordColumn.setCellValueFactory(new PropertyValueFactory<>("passwordField"));

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
        accountColumn.setComparator(Comparator.naturalOrder());
        passwordStrengthColumn.setComparator(Comparator.naturalOrder());

        // 使单元格可编辑
        setColumnEditable(true, new TableColumn[]{domainNameColumn, descriptionColumn, accountColumn});

        contentTableView.setItems(FXCollections.observableList(passwordRecordList));
    }


    private void setColumnEditable(boolean isEdit, TableColumn<Object, String>... column) {

        for (TableColumn<Object, String> tableColumn : column) {
            if (isEdit) {
                tableColumn.setEditable(true);
                tableColumn.setCellFactory(TextFieldTableCell.forTableColumn());
            }
        }
    }


    @FXML
    void commitNewDescription(TableColumn.CellEditEvent<PasswordRecord, String> event) {
        PasswordRecord rowValue = event.getRowValue();
        rowValue.setDescription(event.getNewValue());
        boolean isSuccess = passwordService.updateDescription(rowValue);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "描述修改成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "描述修改失败，请联系管理员处理");
        }
    }

    @FXML
    void commitNewDomainName(TableColumn.CellEditEvent<PasswordRecord, String> event) {
        PasswordRecord rowValue = event.getRowValue();
        rowValue.setDomainName(event.getNewValue());
        boolean isSuccess = passwordService.updateDomainName(rowValue);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "域名修改成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "域名修改失败，请联系管理员处理");
        }
    }

    @FXML
    void commitNewPassword(TableColumn.CellEditEvent<PasswordRecord, TextField> event) {
        PasswordRecord rowValue = event.getRowValue();
        String password = event.getNewValue().getText();
        boolean flag = checkPassword(password);
        if (flag) {
            rowValue.setPassword(password);
            boolean isSuccess = passwordService.updatePassword(rowValue);
            if (isSuccess) {
                AlertUtils.alert(Alert.AlertType.INFORMATION, "密码修改成功");
            } else {
                AlertUtils.alert(Alert.AlertType.WARNING, "密码修改失败，请联系管理员处理");
            }
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "密码格式错误，请使用大小写英文字母、数字以及下列符号`~!@#$%^&*()_+-=[]{}\\\\|;:'\\\",<.>/?\"");
        }
    }

    private boolean checkPassword(String password) {
        int len = password.length();
        for (int i = 0; i < len; i++) {
            if (! PasswordStrength.ALLOW_STRINGS.contains(String.valueOf(password.charAt(i)))) {
                return false;
            }
        }
        return true;
    }

    @FXML
    void commitNewAccount(TableColumn.CellEditEvent<PasswordRecord, String> event) {
        PasswordRecord rowValue = event.getRowValue();
        rowValue.setAccount(event.getNewValue());
        boolean isSuccess = passwordService.updateAccount(rowValue);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "账号修改成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "账号修改失败，请联系管理员处理");
        }
    }


    @FXML
    public void deleteSelected(MouseEvent mouseEvent) {
        Optional<ButtonType> buttonType = AlertUtils.alert(Alert.AlertType.CONFIRMATION, "确认删除吗？");
        if (!ObjectUtils.nullSafeEquals(buttonType.get(), ButtonType.OK)) return;

        ObservableList<PasswordRecord> items = checkboxColumn.getTableView().getItems();
        List<PasswordRecord> deleteList = new LinkedList<>();
        for (PasswordRecord item : items) {
            if (item.getSelected().isSelected()) {
                deleteList.add(item);
            }
        }
        if (ObjectUtils.isEmpty(deleteList)) return;

        boolean isSuccess = passwordService.deleteByList(deleteList);
        refresh(isSuccess);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "删除成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "删除失败");
        }
    }


    @FXML
    void addOnePasswordRecord(MouseEvent event) {
        PasswordRecord passwordRecord = getNewPasswordRecord();
        if (ObjectUtils.isEmpty(passwordRecord)) return;
        boolean isSuccess = passwordService.insertOne(passwordRecord);
        refresh(isSuccess);
        if (isSuccess) {
            AlertUtils.alert(Alert.AlertType.INFORMATION, "添加成功");
        } else {
            AlertUtils.alert(Alert.AlertType.WARNING, "删除失败");
        }
        removeRecordFromTextField();
    }

    @FXML
    void doSearch(MouseEvent event) {
        String keyword = getSearchKeyWord();
        if (ObjectUtils.isEmpty(keyword)) return;

        passwordRecordList = passwordService.selectByKeyword(keyword);
        searchKeyword = keyword;
        refresh();
    }

    @FXML
    void removeSearchTextField(MouseEvent event) {
        if (!ObjectUtils.isEmpty(searchKeyword)) {
            searchKeyword = null;
            searchTextField.setText(null);
            refresh();
        }
    }

    @FXML
    public void createPassword(MouseEvent mouseEvent) {
        try {
            createPasswordView.start(new Stage());
//            indexView.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        String password = RandomStringUtils.random(new Random().nextInt(8) + 8, PasswordStrength.ALLOW_CHARS);
//        AlertUtils.alert("生成密码成功", "您生成的密码是：" + password);
    }

    private String getSearchKeyWord() {
        return searchTextField.getText();
    }

    private void removeRecordFromTextField() {
        domainNameTextField.setText(null);
        descriptionTextField.setText(null);
        accountTextField.setText(null);
        passwordTextField.setText(null);
    }

    private PasswordRecord getNewPasswordRecord() {
        String domainName = domainNameTextField.getText();
        String description = descriptionTextField.getText();
        String account = accountTextField.getText();
        String password = passwordTextField.getText();

        if (ObjectUtils.isEmpty(domainName) || ObjectUtils.isEmpty(description)
                || ObjectUtils.isEmpty(account) || ObjectUtils.isEmpty(password)) {

            AlertUtils.alert(Alert.AlertType.WARNING, "请完善相关信息");
            return null;
        }
        if (!checkPassword(password)) {
            AlertUtils.alert(Alert.AlertType.WARNING, "密码格式错误，请使用大小写英文字母、数字以及下列符号`~!@#$%^&*()_+-=[]{}\\\\|;:'\\\",<.>/?\"");
        }

        PasswordStrength passwordStrength = PasswordStrength.calculatePasswordStrength(password);

        return new PasswordRecord(domainName, description, account, password, passwordStrength);
    }

    public void refresh() {
        refresh(true);
    }

    public void refresh(boolean flag) {
        if (flag) {
            initIndex();
            contentTableView.refresh();
        }
    }

    public void setPasswordService(PasswordService passwordService) {
        this.passwordService = passwordService;
    }

    public void setCreatePasswordView(CreatePasswordView createPasswordView) {
        this.createPasswordView = createPasswordView;
    }

    public void setIndexView(IndexView indexView) {
        this.indexView = indexView;
    }
}
