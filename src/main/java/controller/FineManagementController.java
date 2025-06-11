package controller;

import db.DBConnection;
import dto.FineManagement;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class FineManagementController implements Initializable {

    public TableView tblFines;
    @FXML
    private TableColumn colDates;

    @FXML
    private TableColumn colFine;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colTitle;

    long date=System.currentTimeMillis();
    Date currentDate=new Date(date);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDates.setCellValueFactory(new PropertyValueFactory<>("numberOfDates"));
        colFine.setCellValueFactory(new PropertyValueFactory<>("totalFine"));
        List<FineManagement> fineManagementList=new ArrayList<>();
        try {
            Connection connection = DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("select id,title,name,datediff(?, due_date) as num_dates from issue_books where due_date<? and status=?");
            pst.setDate(1,currentDate);
            pst.setDate(2,currentDate);
            pst.setString(3,"pending");
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                fineManagementList.add(new FineManagement(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("title"),
                        resultSet.getInt("num_dates"),
                        resultSet.getInt("num_dates")*10
                ));
            }
           tblFines.setItems(FXCollections.observableArrayList(fineManagementList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
