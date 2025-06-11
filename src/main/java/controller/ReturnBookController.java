package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReturnBookController {

    @FXML
    private Label lblBookDetail;

    @FXML
    private Label lblDueDate;

    @FXML
    private Label lblIssueDate;

    @FXML
    private Label lblIssueId;

    @FXML
    private Label lblName;

    @FXML
    private Label lblTitle;

    @FXML
    private TextField txtBookId;

    @FXML
    private TextField txtStudentId;

    @FXML
    void onClickFindBook(ActionEvent event) {
        if(!txtBookId.getText().isEmpty()&&!txtStudentId.getText().isEmpty()){
            findBookDetails();
        }
    }

    @FXML
    void onClickReturnBook(ActionEvent event) {
        try {
            boolean isReturned=returnBook();
            if (isReturned){
                new Alert(Alert.AlertType.INFORMATION,"book return success").show();
            }else {
                new Alert(Alert.AlertType.INFORMATION, "book return failed").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void findBookDetails(){
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM issue_books WHERE book_id=? AND student_id=? AND status=?");
            pst.setInt(1,Integer.parseInt(txtBookId.getText()));
            pst.setInt(2,Integer.parseInt(txtStudentId.getText()));
            pst.setString(3,"pending");
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                lblIssueId.setText(resultSet.getString(1));
                lblTitle.setText(resultSet.getString(3));
                lblName.setText(resultSet.getString(5));
                lblIssueDate.setText(resultSet.getString(6));
                lblDueDate.setText(resultSet.getString(7));
                lblBookDetail.setText("");
            }else {
                lblBookDetail.setText("No record found");
                lblIssueId.setText("");
                lblTitle.setText("");
                lblName.setText("");
                lblIssueDate.setText("");
                lblDueDate.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private boolean returnBook() throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("UPDATE issue_books SET status=? WHERE book_id=? AND student_id=? AND status=?");
            pst.setString(1,"returned");
            pst.setInt(2,Integer.parseInt(txtBookId.getText()));
            pst.setInt(3,Integer.parseInt(txtStudentId.getText()));
            pst.setString(4,"pending");
            boolean isReturned = pst.executeUpdate() > 0;
            if(isReturned){
                if(updateBookQty()){
                    connection.commit();
                    return true;
                }
            }
            connection.rollback();
            return false;
        }finally {
            connection.setAutoCommit(true);
        }
    }

    private boolean updateBookQty(){
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("UPDATE books SET qty=qty+1 WHERE id=?");
            pst.setInt(1,Integer.parseInt(txtBookId.getText()));
            return pst.executeUpdate()>0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
