package ru.dolgov.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.entity.Contact;
import ru.dolgov.webservice.repository.Repository;

import java.io.IOException;

/**
 * Created by Михалыч on 23.05.2017.
 */
@Controller
@RequestMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {

    @Autowired
    private Repository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void addContact(@RequestBody String json){
        try {
            add(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DbServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/contacts/{name}", method = RequestMethod.GET)
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    public Contact getContactByName(@PathVariable String name){
        Contact contact = null;
        try {
            contact = repository.getByName(name);
        } catch (DbServiceException e) {
            e.printStackTrace();
        }
        return contact;
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateContact(@RequestBody String json){
        try {
            update(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DbServiceException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteContact(@RequestBody String json){
        try {
            delete(json);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DbServiceException e) {
            e.printStackTrace();
        }
    }

    private void add(String json) throws IOException, DbServiceException {
        Contact contact = new ObjectMapper().readValue(json, Contact.class);
        repository.add(contact);
    }

    private void update(String json) throws IOException, DbServiceException {
        Contact contact = new ObjectMapper().readValue(json, Contact.class);
        repository.update(contact);
    }

    private void delete(String json) throws IOException, DbServiceException {
        Contact contact = new ObjectMapper().readValue(json, Contact.class);
        repository.delete(contact);
    }
}
