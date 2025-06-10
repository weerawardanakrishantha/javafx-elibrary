package repository.custom.impl;

import db.DBConnection;
import entity.UserEntity;
import repository.custom.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ManageUserRepositoryImpl implements UserRepository {
    @Override
    public void add(UserEntity entity) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO users VALUES(?,?,?,?,?)");
        pst.setInt(1,entity.getId());
        pst.setString(2,entity.getName());
        pst.setString(3,entity.getPassword());
        pst.setString(4,entity.getEmail());
        pst.setString(5,entity.getContact());
        int i = pst.executeUpdate();
    }

    @Override
    public List<UserEntity> getAll() throws SQLException {
        List<UserEntity> userEntityList=new ArrayList<>();
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM users");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()){
            userEntityList.add(new UserEntity(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }
        return userEntityList;
    }

    @Override
    public void update(UserEntity entity) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("UPDATE users SET name=?,password=?,email=?,contact=? WHERE id=?");
        pst.setString(1,entity.getName());
        pst.setString(2,entity.getPassword());
        pst.setString(3,entity.getEmail());
        pst.setString(4,entity.getContact());
        pst.setInt(5,entity.getId());

        int i = pst.executeUpdate();
    }

    @Override
    public Boolean delete(Integer id) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM users WHERE id=?");
        pst.setInt(1,id);
        return  pst.executeUpdate()>0;
    }
}
