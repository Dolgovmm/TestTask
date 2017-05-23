package ru.dolgov.webservice.dbservice;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * Created by Михалыч on 23.05.2017.
 */
public class DbServiceImpl implements DbService {

    @Override
    public void add(Contact entity) throws DbServiceException{
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);//todo: add return id of saved entity
            transaction.commit();
        } catch (HibernateException ex) {
            throw new DbServiceException(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public Contact getByName(String name) throws DbServiceException{
        Session session = null;
        Contact contact = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            contact = session.load(Contact.class, name);
        } catch (HibernateException ex) {
            throw new DbServiceException(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return contact;
    }

    @Override
    public void update(Contact entity) throws DbServiceException{
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            throw new DbServiceException(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @Override
    public void delete(Contact entity) throws DbServiceException{
        Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.delete(entity);
            transaction.commit();
        } catch (HibernateException ex) {
            throw new DbServiceException(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
