package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        login();
    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user-registration.fxml"))));
        stage.show();;
    }

    public void login(){
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from users where name=? and password=?");
            pst.setString(1,txtUsername.getText());
            pst.setString(2,txtPassword.getText());
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                new Alert(Alert.AlertType.INFORMATION,"Login success").show();
                Stage stage=new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/home.fxml"))));
                stage.show();
            }else {
                new Alert(Alert.AlertType.INFORMATION,"Invalid Username or password").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
