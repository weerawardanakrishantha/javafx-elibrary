package service.custom.impl;

import dto.Book;
import entity.BookEntity;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.BookRepository;
import service.custom.BookService;
import util.RepositoryType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookServiceImpl implements BookService {

    BookRepository bookRepository= DaoFactory.getInstance().getRepositoryType(RepositoryType.BOOK);

    ModelMapper modelMapper=new ModelMapper();

    @Override
    public List<Book> getAllBooks() throws SQLException {
        List<BookEntity> bookEntityList=bookRepository.getAll();
        List<Book> bookList=new ArrayList<>();
        for(BookEntity bookEntity:bookEntityList){
            bookList.add(modelMapper.map(bookEntity, Book.class));
        }
        return bookList;
    }

    @Override
    public void addBook(Book book) throws SQLException {
        bookRepository.add(modelMapper.map(book,BookEntity.class));
    }

    @Override
    public void updateBook(Book book) throws SQLException {
        bookRepository.update(modelMapper.map(book, BookEntity.class));
    }

    @Override
    public Boolean deleteBook(Long id) throws SQLException {
        return bookRepository.delete(id);
    }
}
