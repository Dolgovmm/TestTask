package ru.dolgov.webservice.dbservice;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * @author M.Dolgov
 * @date 23.05.2017
 */
public class HibernateSessionFactory {
    static final Logger logger = LoggerFactory.getLogger(HibernateSessionFactory.class);
	private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if (sessionFactory == null){
			//try {
				sessionFactory = new Configuration().configure().buildSessionFactory();
				logger.debug("Hibernate session factory created");
			//} catch ()
        }
        return sessionFactory;
    }

    public static void shutDown(){
        sessionFactory.close();
		logger.debug("Hibernate session factory shutdown");
    }
}
