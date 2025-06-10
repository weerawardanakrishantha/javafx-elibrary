package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    void btnDefaulterList(ActionEvent event) {

    }

    @FXML
    void btnIssueBook(ActionEvent event) {

    }

    @FXML
    void btnManageBooks(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manage-books.fxml"))));
        stage.show();;
    }

    @FXML
    void btnManageStudents(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manage-students.fxml"))));
        stage.show();
    }

    @FXML
    void btnManageUsers(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/manage-user.fxml"))));
        stage.show();
    }

    @FXML
    void btnReturnBook(ActionEvent event) {

    }

    @FXML
    void btnViewIssuedBooks(ActionEvent event) {

    }

    @FXML
    void btnViewRecord(ActionEvent event) {

    }

}
