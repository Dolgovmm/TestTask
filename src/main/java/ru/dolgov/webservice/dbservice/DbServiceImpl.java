package ru.dolgov.webservice.dbservice;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * Created by Михалыч on 23.05.2017.
 */
public class DbServiceImpl implements DbService {

    @Override
    public long add(Contact entity) throws DbServiceException{
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);//todo: add return id of saved entity
            transaction.commit();
            return 1;
        } catch (HibernateException ex) {
            transaction.rollback();
            throw new DbServiceException(ex.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
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
    public long remove(long id) {
        return 0;
    }
}
