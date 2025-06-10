package repository;

import java.sql.SQLException;

public interface CrudRepository <T, ID> extends SuperRepository{
    public void add(T entity) throws SQLException;
}
