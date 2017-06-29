package ru.dolgov.webservice.dbservice;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.dolgov.webservice.entity.Contact;
import ru.dolgov.webservice.entity.Contact_;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author M.Dolgov
 * @date 23.05.2017
 */
public class DbServiceImpl implements DbService {
	static final Logger logger = LoggerFactory.getLogger(DbServiceImpl.class);


    @Override
    public void add(Contact entity) throws DbServiceException{
		Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
        } catch (HibernateException e) {
			throw new DbServiceException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List<Contact> getByName(String firstName) throws DbServiceException{
		Session session = null;
        List<Contact> list = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery criteria = criteriaBuilder.createQuery(Contact.class);
            Root<Contact> contactRoot = criteria.from(Contact.class);
            criteria.select(contactRoot);
            criteria.where(criteriaBuilder.equal(contactRoot.get(Contact_.firstName), firstName));
            list = session.createQuery(criteria).getResultList();
        } catch (HibernateException e) {
            throw new DbServiceException(e.getMessage());
        } finally {
            closeSession(session);
        }
        return list;
    }

    @Override
    public void update(Contact entity) throws DbServiceException{
		Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            session.update(entity);
            transaction.commit();
        } catch (HibernateException e) {
			throw new DbServiceException(e.getMessage());
        } finally {
            closeSession(session);
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
        } catch (HibernateException e) {
            throw new DbServiceException(e.getMessage());
        } finally {
            closeSession(session);
        }
    }

    private void closeSession(Session session) {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }
}
