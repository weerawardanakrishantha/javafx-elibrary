package repository.custom.impl;

import db.DBConnection;
import entity.StudentEntity;
import repository.custom.StudentRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StudentRepositoryImpl implements StudentRepository {
    @Override
    public void add(StudentEntity entity) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO students VALUES(?,?,?,?)");
        pst.setLong(1,entity.getId());
        pst.setString(2,entity.getName());
        pst.setString(3,entity.getAddress());
        pst.setString(4,entity.getCourse());
        int i = pst.executeUpdate();
    }

    @Override
    public List<StudentEntity> getAll() throws SQLException {
        List<StudentEntity> studentEntityList=new ArrayList<>();
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM students");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()){
            studentEntityList.add(new StudentEntity(
                    resultSet.getLong(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }
        return studentEntityList;
    }

    @Override
    public void update(StudentEntity entity) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("UPDATE students SET name=?,address=?,course=? WHERE id=?");
        pst.setString(1,entity.getName());
        pst.setString(2,entity.getAddress());
        pst.setString(3,entity.getCourse());
        pst.setLong(4,entity.getId());
        int i = pst.executeUpdate();
    }

    @Override
    public Boolean delete(Long id) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM students WHERE id=?");
        pst.setLong(1,id);
        return pst.executeUpdate()>0;
    }
}
