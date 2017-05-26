package ru.dolgov.webservice.repository;

import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * @author M.Dolgov
 * @date 22.05.2017
 */
public interface Repository {

    public void add(Contact entity) throws DbServiceException;

    public List<Contact> getByName(String name) throws DbServiceException;

    public void update(Contact entity) throws DbServiceException;

    public void delete(Contact entity) throws DbServiceException;
}
