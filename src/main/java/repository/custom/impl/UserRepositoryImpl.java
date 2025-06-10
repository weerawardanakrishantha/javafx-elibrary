package repository.custom.impl;

import db.DBConnection;
import dto.User;
import entity.UserEntity;
import repository.custom.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class UserRepositoryImpl implements UserRepository {

    @Override
    public void add(UserEntity userEntity) throws SQLException {
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO users(name,password,email,contact) VALUES(?,?,?,?)");
        pst.setString(1,userEntity.getName());
        pst.setString(2,userEntity.getPassword());
        pst.setString(3,userEntity.getEmail());
        pst.setString(4,userEntity.getContact());

        int i = pst.executeUpdate();
    }
}
