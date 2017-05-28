package ru.dolgov.webservice.dbservice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author M.Dolgov
 * @date 23.05.2017
 */
public class HibernateSessionFactory {
    static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactory.class);
	private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
				sessionFactory = new Configuration().configure().buildSessionFactory();
				logger.debug("Hibernate session factory created");
        }
        return sessionFactory;
    }

    public static void shutDown(){
        sessionFactory.close();
		logger.debug("Hibernate session factory shutdown");
    }
}
