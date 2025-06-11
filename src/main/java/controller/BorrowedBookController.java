package controller;

import db.DBConnection;
import dto.BorrowedBooks;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BorrowedBookController implements Initializable {

    @FXML
    private TableColumn colAuthor;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tblBooks;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colAuthor.setCellValueFactory(new PropertyValueFactory<>("author"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        try {
            List<BorrowedBooks> borrowedBooksList=new ArrayList<>();
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select books.id,books.title,books.author,issue_books.status from books inner join issue_books on books.id=issue_books.book_id where status=?");
            pst.setString(1,"pending");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                borrowedBooksList.add(new BorrowedBooks(
                        resultSet.getInt("id"),
                        resultSet.getString("title"),
                        resultSet.getString("author"),
                        resultSet.getString("status")
                ));
            }
            tblBooks.setItems(FXCollections.observableArrayList(borrowedBooksList));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
