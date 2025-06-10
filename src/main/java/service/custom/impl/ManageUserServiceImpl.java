package service.custom.impl;

import dto.User;
import entity.UserEntity;
import org.modelmapper.ModelMapper;
import repository.custom.impl.ManageUserRepositoryImpl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ManageUserServiceImpl {

    ManageUserRepositoryImpl userRepository=new ManageUserRepositoryImpl();

    ModelMapper modelMapper=new ModelMapper();

    public void addUser(User user) throws SQLException {
        userRepository.add(modelMapper.map(user, UserEntity.class));
    }
    public List<User> getAllUsers() throws SQLException {
        List<UserEntity> userEntityList=userRepository.getAll();
        List<User> userList=new ArrayList<>();
        for(UserEntity userEntity:userEntityList){
            userList.add(modelMapper.map(userEntity,User.class));
        }
        return userList;
    }
    public void updateUser(User user) throws SQLException {
        userRepository.update(modelMapper.map(user, UserEntity.class));
    }
    public Boolean deleteUser(Integer id) throws SQLException {
        return userRepository.delete(id);
    }
}
