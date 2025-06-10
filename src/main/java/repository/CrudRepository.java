package repository;

import java.sql.SQLException;
import java.util.List;

public interface CrudRepository <T, ID> extends SuperRepository{
    public void add(T entity) throws SQLException;
    public List<T> getAll() throws SQLException;
    public void update(T entity) throws SQLException;
    public Boolean delete(ID id) throws SQLException;
}
