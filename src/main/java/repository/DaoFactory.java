package repository;

import repository.custom.impl.BookRepositoryImpl;
import repository.custom.impl.StudentRepositoryImpl;
import repository.custom.impl.UserRepositoryImpl;
import util.RepositoryType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){
    }

    public static DaoFactory getInstance() {
        return instance==null?instance=new DaoFactory():instance;
    }

    public <T>T getRepositoryType(RepositoryType type){
        switch (type){
            case USER : return (T) new UserRepositoryImpl();
            case BOOK: return (T) new BookRepositoryImpl();
            case STUDENT:return (T) new StudentRepositoryImpl();
        }
        return null;
    }
}
