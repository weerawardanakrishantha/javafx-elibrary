package service.custom;

import dto.User;
import service.SuperService;

import java.sql.SQLException;

public interface UserService extends SuperService {
    public void addUser(User user) throws SQLException;
}
