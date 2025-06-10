package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserFormController {

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUsername;

    @FXML
    void btnLoginOnAction(ActionEvent event) {

    }

    @FXML
    void btnSignupOnAction(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/user-registration.fxml"))));
        stage.show();;
    }

}
