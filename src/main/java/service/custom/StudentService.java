package service.custom;

import dto.Student;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface StudentService extends SuperService {
    public void addStudent(Student student) throws SQLException;
    public void updateStudent(Student student) throws SQLException;
    public List<Student> getAllStudent() throws SQLException;
    public Boolean deleteStudent(Long id) throws SQLException;
}
