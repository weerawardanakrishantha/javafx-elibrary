package controller;

import dto.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import service.custom.impl.ManageUserServiceImpl;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageUserController implements Initializable {

    @FXML
    private TableColumn colContact;

    @FXML
    private TableColumn colEmail;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colPassword;

    @FXML
    private TableView tblUsers;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPassword;

    ManageUserServiceImpl userService=new ManageUserServiceImpl();

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            userService.addUser(new User(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtPassword.getText(),
                    txtEmail.getText(),
                    txtContact.getText()
            ));
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Boolean isDeleted=userService.deleteUser(Integer.parseInt(txtId.getText()));
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"deleted success").show();
                loadTable();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"delete failed").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            userService.updateUser(new User(
                    Integer.parseInt(txtId.getText()),
                    txtName.getText(),
                    txtPassword.getText(),
                    txtEmail.getText(),
                    txtContact.getText()
            ));
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblClicked(MouseEvent event) {
        loadSelectedRow();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();;
    }

    private void loadTable(){
        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            List<User> userList=userService.getAllUsers();
            tblUsers.setItems(FXCollections.observableArrayList(userList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSelectedRow(){
        ObservableList<User> user = tblUsers.getSelectionModel().getSelectedItems();
        txtId.setText(user.get(0).getId().toString());
        txtName.setText(user.get(0).getName());
        txtPassword.setText(user.get(0).getPassword());
        txtEmail.setText(user.get(0).getEmail());
        txtContact.setText(user.get(0).getContact());
    }
}
