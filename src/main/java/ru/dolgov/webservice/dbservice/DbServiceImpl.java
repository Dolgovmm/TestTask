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
        logger.debug("add Contact method DbService with contact: " + entity.toString());
		Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
			logger.debug("get session");
            Transaction transaction = session.beginTransaction();
			logger.debug("begin transaction");
            session.save(entity);
			logger.debug("save entity " + entity.toString());
            transaction.commit();
			logger.debug("transaction commit");
        } catch (HibernateException e) {
            logger.error("error save contact: " + entity.toString() + " to DB with message: " + e.getMessage());
			throw new DbServiceException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
				session.close();
				logger.debug("close session");
            }
        }
    }

    @Override
    public List<Contact> getByName(String firstName) throws DbServiceException{
        logger.debug("get by name Contact method DbService with name: " + firstName);
		Session session = null;
        List<Contact> list;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
			logger.debug("get session");
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
			logger.debug("get criteria builder");
            CriteriaQuery criteria = criteriaBuilder.createQuery(Contact.class);
			logger.debug("create query");
            Root<Contact> contactRoot = criteria.from(Contact.class);
			logger.debug("get contact root");
            criteria.select(contactRoot);
			logger.debug("criteria select");
            criteria.where(criteriaBuilder.equal(contactRoot.get(Contact_.firstName), firstName));
			logger.debug("selecting on name: " + firstName);
            list = session.createQuery(criteria).getResultList();
			logger.debug("get list from query with " + list.size() + " elements");
        } catch (HibernateException e) {
			logger.error("error get contact by name: " + firstName + " from DB with message: " + e.getMessage());
            throw new DbServiceException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
				logger.debug("close session");
            }
        }
        return list;
    }

    @Override
    public void update(Contact entity) throws DbServiceException{
        logger.debug("update Contact method DbService with contact: " + entity.toString());
		Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
			logger.debug("get session");
            Transaction transaction = session.beginTransaction();
			logger.debug("begin transaction");
            session.update(entity);
			logger.debug("update entity " + entity.toString());
            transaction.commit();
			logger.debug("commit transaction");
        } catch (HibernateException e) {
            logger.error("error update contact: " + entity.toString() + " to DB with message: " + e.getMessage());
			throw new DbServiceException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
				logger.debug("close session");
            }
        }
    }

    @Override
    public void delete(Contact entity) throws DbServiceException{
        logger.debug("delete Contact method DbService with contact: " + entity.toString());
		Session session = null;
        try {
            session = HibernateSessionFactory.getSessionFactory().openSession();
			logger.debug("get session");
            Transaction transaction = session.beginTransaction();
			logger.debug("begin transaction");
            session.delete(entity);
			logger.debug("delete entity " + entity.toString());
            transaction.commit();
			logger.debug("commit transaction");
        } catch (HibernateException e) {
			logger.error("error delete contact: " + entity.toString() + " from DB with message: " + e.getMessage());
            throw new DbServiceException(e.getMessage());
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
				logger.debug("close session");
            }
        }
    }
}
