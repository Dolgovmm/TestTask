package ru.dolgov.webservice.dbservice;

import org.hibernate.HibernateException;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * Created by Михалыч on 22.05.2017.
 */
public interface DbService {

    public long add(Contact entity) throws DbServiceException;

    public Contact getById(long id);

    public List<Contact> getAll();

    public long update(Contact entity);

    public long remove(long id);
}
