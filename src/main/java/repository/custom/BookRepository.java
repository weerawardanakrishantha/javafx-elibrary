package repository.custom;

import entity.BookEntity;
import repository.CrudRepository;

public interface BookRepository extends CrudRepository<BookEntity,Long> {
}
