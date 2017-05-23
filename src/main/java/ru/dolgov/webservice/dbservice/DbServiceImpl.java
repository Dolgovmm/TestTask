package ru.dolgov.webservice.dbservice;

import ru.dolgov.webservice.entity.Contact;

import java.util.List;

/**
 * Created by Михалыч on 23.05.2017.
 */
public class DbServiceImpl implements DbService {

    @Override
    public long add(Contact entity) {
        return 0;
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
