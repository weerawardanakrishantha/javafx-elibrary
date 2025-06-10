package repository.custom;

import dto.User;
import entity.UserEntity;
import repository.CrudRepository;

import java.sql.SQLException;

public interface UserRepository extends CrudRepository<UserEntity, Integer> {

}
