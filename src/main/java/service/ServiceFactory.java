package service;

import service.custom.impl.BookServiceImpl;
import service.custom.impl.StudentServiceImpl;
import service.custom.impl.UserServiceImpl;
import util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){

    }

    public static ServiceFactory getInstance() {
        return instance==null?instance=new ServiceFactory():instance;
    }

    public <T extends SuperService>T getServiceType(ServiceType type){
        switch (type){
            case USER : return (T) new UserServiceImpl();
            case BOOK: return (T) new BookServiceImpl();
            case STUDENT:return (T) new StudentServiceImpl();
        }
        return null;
    }
}
