package controller;

import db.DBConnection;
import dto.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.UserService;
import service.custom.impl.UserServiceImpl;
import util.ServiceType;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRegistrationController {

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUsername;

    UserService service= ServiceFactory.getInstance().getServiceType(ServiceType.USER);

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user-form.fxml"))));
        stage.show();
    }

    @FXML
    void btnRegisterOnAction(ActionEvent event) {
        try {
            if(validation()) {
                service.addUser(new User(
                        txtUsername.getText(),
                        txtPassword.getText(),
                        txtEmail.getText(),
                        txtContact.getText()
                ));
                new Alert(Alert.AlertType.INFORMATION, "Registration Success").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Boolean validation(){
        if(txtUsername.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"enter valid user name").show();
            return false;
        }
        if (txtPassword.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"enter valid password").show();
            return false;
        }
        if (txtEmail.getText().isEmpty() ||!txtEmail.getText().matches("^(.+)@(.+)$*")){
            new Alert(Alert.AlertType.INFORMATION,"enter valid email").show();
            return false;
        }
        if(txtContact.getText().isEmpty()){
            new Alert(Alert.AlertType.INFORMATION,"enter valid contact number").show();
            return false;
        }
        return true;
    }

    private boolean duplicateUser(){
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from users where name=?");
            pst.setString(1,txtUsername.getText());
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public void mouseLeave(MouseEvent mouseEvent) {
        if(duplicateUser()){
            new Alert(Alert.AlertType.INFORMATION,"user already exists").show();;
        }
    }
}
