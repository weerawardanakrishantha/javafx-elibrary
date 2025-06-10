package service.custom;

import dto.Book;
import service.SuperService;

import java.sql.SQLException;
import java.util.List;

public interface BookService extends SuperService {
    public List<Book> getAllBooks() throws SQLException;
    public void addBook(Book book) throws SQLException;
    public void updateBook(Book book) throws SQLException;
    public Boolean deleteBook(Long id) throws SQLException;
}
