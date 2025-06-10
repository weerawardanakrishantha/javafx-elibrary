package service.custom.impl;

import dto.User;
import entity.UserEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.UserRepository;
import service.custom.UserService;
import util.RepositoryType;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    UserRepository repository= DaoFactory.getInstance().getRepositoryType(RepositoryType.USER);

    ModelMapper modelMapper=new ModelMapper();

    public void addUser(User user) throws SQLException {
        repository.add(modelMapper.map(user, UserEntity.class));
    }

}
