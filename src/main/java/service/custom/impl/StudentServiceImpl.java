package service.custom.impl;

import dto.Student;
import entity.StudentEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.StudentRepository;
import service.custom.StudentService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentServiceImpl implements StudentService {

    StudentRepository studentRepository= DaoFactory.getInstance().getRepositoryType(RepositoryType.STUDENT);

    ModelMapper modelMapper=new ModelMapper();

    @Override
    public void addStudent(Student student) throws SQLException {
        studentRepository.add(modelMapper.map(student, StudentEntity.class));

    }

    @Override
    public void updateStudent(Student student) throws SQLException {
        studentRepository.update(modelMapper.map(student, StudentEntity.class));
    }

    @Override
    public List<Student> getAllStudent() throws SQLException {
        List<StudentEntity> studentEntityList=studentRepository.getAll();
        List<Student> studentList=new ArrayList<>();
        for(StudentEntity studentEntity: studentEntityList){
            studentList.add(modelMapper.map(studentEntity,Student.class));
        }
        return studentList;
    }

    @Override
    public Boolean deleteStudent(Long id) throws SQLException {
        return studentRepository.delete(id);
    }
}
