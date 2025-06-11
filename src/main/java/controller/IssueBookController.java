package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.sql.*;

public class IssueBookController {

    public TextField txtBookId;
    public TextField txtStudentId;
    public Label lblBookIdValid;
    public Label iblStudentIdValid;
    public DatePicker dateIssueDate;
    public DatePicker dateDueDate;
    public Label lblBookId1;
    @FXML
    private Label lblAddress;

    @FXML
    private Label lblAuthor;

    @FXML
    private Label lblBookId;

    @FXML
    private Label lblCourse;

    @FXML
    private Label lblName;

    @FXML
    private Label lblQuantity;

    @FXML
    private Label lblSudentId;

    @FXML
    private Label lblTitle;

    @FXML
    void bookIdMouseExited(MouseEvent event) {
        if(!txtBookId.getText().isEmpty()){
            getBookDetails();
        }
    }

    @FXML
    void btnIssueOnAction(ActionEvent event) {
        try {
            if(lblQuantity.getText().equals("0")){
                new Alert(Alert.AlertType.INFORMATION,"book is not available").show();
            }else{
                if(isAlreadyAllocated()){
                    new Alert(Alert.AlertType.INFORMATION,"book is already lend by current student").show();
                }else {
                    if (issueBook()) {
                        int initialQty = Integer.parseInt(lblQuantity.getText());
                        new Alert(Alert.AlertType.INFORMATION, "book issue success").show();
                        lblQuantity.setText(Integer.toString(initialQty - 1));
                    } else {
                        new Alert(Alert.AlertType.INFORMATION, "book issue failed");
                    }
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void studentIdMouseExited(MouseEvent event) {
        if(!txtStudentId.getText().isEmpty()){
            getStudentDetails();
        }
    }

    private void getBookDetails(){
        try {
            Connection connection= DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM books WHERE id=?");
            pst.setLong(1,Long.parseLong(txtBookId.getText()));
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()){
                lblBookId.setText(resultSet.getString(1));
                lblTitle.setText(resultSet.getString(2));
                lblAuthor.setText(resultSet.getString(3));
                lblQuantity.setText(resultSet.getString(4));
                lblBookIdValid.setText("");
            }else {
                lblBookIdValid.setText("Invalid BookId");
                lblBookId.setText("");
                lblTitle.setText("");
                lblAuthor.setText("");
                lblQuantity.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getStudentDetails(){
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM students WHERE id=?");
            pst.setLong(1,Long.parseLong(txtStudentId.getText()));
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()){
                lblSudentId.setText(resultSet.getString(1));
                lblName.setText(resultSet.getString(2));
                lblAddress.setText(resultSet.getString(3));
                lblCourse.setText(resultSet.getString(4));
                iblStudentIdValid.setText("");
            }else{
                iblStudentIdValid.setText("Invalid StudentId");
                lblSudentId.setText("");
                lblName.setText("");
                lblAddress.setText("");
                lblCourse.setText("");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Boolean issueBook() throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
            PreparedStatement pst = connection.prepareStatement("INSERT INTO issue_books(book_id,title,student_id,name,issue_date,due_date,status) VALUES(?,?,?,?,?,?,?)");
            pst.setLong(1,Long.parseLong(lblBookId.getText()));
            pst.setString(2,lblTitle.getText());
            pst.setLong(3,Long.parseLong(lblSudentId.getText()));
            pst.setString(4,lblName.getText());
            pst.setDate(5, Date.valueOf(dateIssueDate.getValue().toString()));
            pst.setDate(6,Date.valueOf(dateDueDate.getValue().toString()));
            pst.setString(7,"pending");
            boolean isIssued = pst.executeUpdate() > 0;
            if(isIssued){
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

    private Boolean updateBookQty() throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("UPDATE books SET qty=qty-1 WHERE id=?");
        pst.setLong(1,Long.parseLong(lblBookId.getText()));
        return pst.executeUpdate()>0;
    }

    private Boolean isAlreadyAllocated(){
        try {
            Connection connection=DBConnection.getInstance().getConnection();
            PreparedStatement pst = connection.prepareStatement("SELECT * FROM issue_books WHERE book_id=? AND student_id=? AND status=?");
            pst.setLong(1,Long.parseLong(lblBookId.getText()));
            pst.setLong(2,Long.parseLong(lblSudentId.getText()));
            pst.setString(3,"pending");
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
}
