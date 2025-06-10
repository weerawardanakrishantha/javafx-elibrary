package controller;

import dto.Book;
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
import service.ServiceFactory;
import service.custom.BookService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageBooksController implements Initializable {

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colQty;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tblBooks;

    @FXML
    private TextField txtAuthor;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtTitle;

    BookService bookService=ServiceFactory.getInstance().getServiceType(ServiceType.BOOK);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            bookService.addBook(
                    new Book(
                            Long.parseLong(txtId.getText()),
                            txtTitle.getText(),
                            txtAuthor.getText(),
                            Integer.parseInt(txtQuantity.getText())
                    )
            );
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Boolean isDeleted=bookService.deleteBook(Long.parseLong(txtId.getText()));
            if(isDeleted){
                new Alert(Alert.AlertType.INFORMATION,"delete success").show();
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
            bookService.updateBook(new Book(
                    Long.parseLong(txtId.getText()),
                    txtTitle.getText(),
                    txtAuthor.getText(),
                    Integer.parseInt(txtQuantity.getText())
            ));
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }
    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        try {
            List<Book> books=bookService.getAllBooks();
            tblBooks.setItems(FXCollections.observableArrayList(books));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void getSelectedRow(){
        ObservableList<Book> book = tblBooks.getSelectionModel().getSelectedItems();
        txtId.setText(book.get(0).getId().toString());
        txtTitle.setText(book.get(0).getTitle());
        txtAuthor.setText(book.get(0).getAuthor());
        txtQuantity.setText(book.get(0).getQty().toString());
    }

    public void tblClicked(MouseEvent mouseEvent) {
        getSelectedRow();
    }
}
