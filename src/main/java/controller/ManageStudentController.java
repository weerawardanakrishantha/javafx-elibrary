package controller;

import dto.Student;
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
import service.custom.StudentService;
import util.ServiceType;

import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;

public class ManageStudentController implements Initializable {

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colCourse;

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableView tblStudents;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtCourse;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    StudentService studentService= ServiceFactory.getInstance().getServiceType(ServiceType.STUDENT);

    @FXML
    void btnAddOnAction(ActionEvent event) {
        try {
            studentService.addStudent(new Student(
                    Long.parseLong(txtId.getText()),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtCourse.getText()
            ));
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            Boolean isDeleted=studentService.deleteStudent(Long.parseLong(txtId.getText()));
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
            studentService.updateStudent(new Student(
                    Long.parseLong(txtId.getText()),
                    txtName.getText(),
                    txtAddress.getText(),
                    txtCourse.getText()
            ));
            loadTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void tblClicked(MouseEvent event) {
        getSelectedRow();
    }



    private void loadTable(){
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colCourse.setCellValueFactory(new PropertyValueFactory<>("course"));
        try {
            List<Student> studentList=studentService.getAllStudent();
            tblStudents.setItems(FXCollections.observableArrayList(studentList));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadTable();
    }

    private void getSelectedRow(){
        ObservableList<Student> student = tblStudents.getSelectionModel().getSelectedItems();
        txtId.setText(student.get(0).getId().toString());
        txtName.setText(student.get(0).getName());
        txtAddress.setText(student.get(0).getAddress());
        txtCourse.setText(student.get(0).getCourse());
    }
}
