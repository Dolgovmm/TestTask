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

    private DbService dbService;

    public RepositoryImpl() {
        dbService = new DbServiceImpl();
    }

    @Override
    public long add(Contact entity) throws DbServiceException{
        return dbService.add(entity);
    }

    @Override
    public Contact getById(long id) {
        return null;
    }

    @Override
    public List<Contact> getAll() {
        return null;
    }

    @Override
    public long update(Contact entity) {
        return 0;
    }

    @Override
    public long delete(long id) {
        return 0;
    }
}
