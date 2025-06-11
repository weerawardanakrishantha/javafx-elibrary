package controller;

import dto.Book;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import service.ServiceFactory;
import service.custom.BookService;
import util.ServiceType;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    public Label lblAvailableBooks;
    public Label lblIssuedBooks;
    public Label lblStudent;
    public TableView tblBooks;
    public TableColumn colId;
    public TableColumn colTitle;
    public TableColumn colAuthor;
    public TableColumn colQuantity;

    BookService bookService= ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @FXML
    void btnDefaulterList(ActionEvent event) {

    }

    @FXML
    void btnIssueBook(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/issue-book.fxml"))));
        stage.show();;
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
    void btnReturnBook(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/reurn-book.fxml"))));
        stage.show();
    }

    @FXML
    void btnViewIssuedBooks(ActionEvent event) throws IOException {
        Stage stage=new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/borrowed-book.fxml"))));
        stage.show();
    }

    @FXML
    void btnViewRecord(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("qty"));
        try {
            List<Book> books=bookService.getAllBooks();
            tblBooks.setItems(FXCollections.observableArrayList(books));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
