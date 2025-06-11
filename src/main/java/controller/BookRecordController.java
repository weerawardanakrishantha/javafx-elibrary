package controller;

import db.DBConnection;
import dto.BookDetail;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class BookRecordController implements Initializable {

    @FXML
    private TableColumn colDueDate;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colIssuedDate;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colStatus;

    @FXML
    private TableColumn colTitle;

    @FXML
    private TableView tblBookDetails;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colIssuedDate.setCellValueFactory(new PropertyValueFactory<>("issueDate"));
        colDueDate.setCellValueFactory(new PropertyValueFactory<>("dueDate"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        List<BookDetail> bookDetailList=new ArrayList<>();

        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select * from issue_books");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                bookDetailList.add(new BookDetail(
                        resultSet.getInt(1),
                        resultSet.getString(3),
                        resultSet.getString(5),
                        resultSet.getDate(6),
                        resultSet.getDate(7),
                        resultSet.getString(8)
                ));
            }
            tblBookDetails.setItems(FXCollections.observableArrayList(bookDetailList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
