package ru.dolgov.webservice.dbservice;

import org.hibernate.HibernateException;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * @author M.Dolgov
 * @date 22.05.2017
 */
public interface DbService {

    public void add(Contact entity) throws DbServiceException;

    public List<Contact> getByName(String firstName) throws DbServiceException;

    public void update(Contact entity) throws DbServiceException;

    public void delete(Contact entity) throws DbServiceException;
}
