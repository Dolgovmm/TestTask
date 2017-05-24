package ru.dolgov.webservice.repository;

import org.hibernate.HibernateException;
import ru.dolgov.webservice.dbservice.DbService;
import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.dbservice.DbServiceImpl;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * Created by Михалыч on 23.05.2017.
 */
public class RepositoryImpl implements Repository {

    private DbService dbService = new DbServiceImpl();

//    public RepositoryImpl() {
//        System.out.println("create dbService");
//        dbService = new DbServiceImpl();
//    }

    @Override
    public void add(Contact entity) throws DbServiceException{
        dbService.add(entity);
    }

    @Override
    public Contact getByName(String name) throws DbServiceException{
        return dbService.getByName(name);
    }

    @Override
    public void update(Contact entity) throws DbServiceException{
        dbService.update(entity);
    }

    @Override
    public void delete(Contact entity) throws DbServiceException{
        dbService.delete(entity);
    }
}
