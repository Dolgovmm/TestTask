package ru.dolgov.webservice.repository;

import org.hibernate.HibernateException;
import ru.dolgov.webservice.dbservice.DbService;
import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.dbservice.DbServiceImpl;
import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * @author M.Dolgov
 * @date 23.05.2017
 */
public class RepositoryImpl implements Repository {
	static final Logger logger = LoggerFactory.getLogger(RepositoryImpl.class);

    private DbService dbService;

    public RepositoryImpl() {
		logger.debug("begin constructor RepositoryImpl");
        dbService = new DbServiceImpl();
		logger.debug("end constructor RepositoryImpl");
   }

    @Override
    public void add(Contact entity) throws DbServiceException{
        dbService.add(entity);
    }

    @Override
    public List<Contact> getByName(String name) throws DbServiceException{
        return dbService.getByName(name);
    }

    @Override
    public void update(Contact entity) throws DbServiceException{
        dbService.update(entity);
    }

    @Override
    public void delete(Contact entity) throws DbServiceException{
        dbService.delete(entity);
    }
}
