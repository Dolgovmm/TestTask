package ru.dolgov.webservice.repository;

import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * Created by Михалыч on 22.05.2017.
 */
public interface Repository {

    public long add(Contact entity) throws DbServiceException;

    public Contact getById(long id);

    public List<Contact> getAll();

    public long update(Contact entity);

    public long delete(long id);
}
