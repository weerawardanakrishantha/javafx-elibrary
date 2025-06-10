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
    void btnManageStudents(ActionEvent event) {

    }

    @FXML
    void btnManageUsers(ActionEvent event) {

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
