package ru.dolgov.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.dbservice.HibernateSessionFactory;
import ru.dolgov.webservice.entity.Contact;
import ru.dolgov.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

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
    public ResponseEntity<Contact> addContact(@RequestBody String json){
        try {
            Contact contact = new ObjectMapper().readValue(json, Contact.class);
            repository.add(contact);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException | DbServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/contacts/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contact>> getContactByName(@PathVariable String name){
        List<Contact> contacts = null;
        try {
            contacts = repository.getByName(name);
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (DbServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Contact> updateContact(@RequestBody String json){
        try {
            Contact contact = new ObjectMapper().readValue(json, Contact.class);
            repository.update(contact);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException | DbServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Contact> deleteContact(@RequestBody String json){
        try {
            Contact contact = new ObjectMapper().readValue(json, Contact.class);
            repository.delete(contact);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException | DbServiceException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
