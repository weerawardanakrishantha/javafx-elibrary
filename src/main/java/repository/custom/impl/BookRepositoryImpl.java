package repository.custom.impl;

import db.DBConnection;
import entity.BookEntity;
import repository.custom.BookRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepository {
    @Override
    public void add(BookEntity entity) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("INSERT INTO books VALUES(?,?,?,?)");
        pst.setLong(1,entity.getId());
        pst.setString(2,entity.getTitle());
        pst.setString(3,entity.getAuthor());
        pst.setInt(4,entity.getQty());
        int i = pst.executeUpdate();
    }

    @Override
    public List<BookEntity> getAll() throws SQLException {
        List<BookEntity> bookEntityList=new ArrayList<>();
        Connection connection= DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("select * from books");
        ResultSet resultSet = pst.executeQuery();
        while (resultSet.next()){
            bookEntityList.add(new BookEntity(
              resultSet.getLong(1),
              resultSet.getString(2),
              resultSet.getString(3),
              resultSet.getInt(4)
            ));
        }
        return bookEntityList;
    }

    @Override
    public void update(BookEntity entity) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("UPDATE books SET title=?, author=?, qty=? WHERE id=?");
        pst.setString(1,entity.getTitle());
        pst.setString(2,entity.getAuthor());
        pst.setInt(3,entity.getQty());
        pst.setLong(4,entity.getId());
        int i = pst.executeUpdate();
    }

    @Override
    public Boolean delete(Long id) throws SQLException {
        Connection connection=DBConnection.getInstance().getConnection();
        PreparedStatement pst = connection.prepareStatement("DELETE FROM books WHERE id=?");
        pst.setLong(1,id);
        return pst.executeUpdate()>0;
    }
}
