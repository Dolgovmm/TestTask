package ru.dolgov.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dolgov.webservice.dbservice.DbServiceException;
import ru.dolgov.webservice.entity.Contact;
import ru.dolgov.webservice.repository.Repository;

import java.io.IOException;
import java.util.List;

/**
 * @author M.Dolgov
 * @date 23.05.2017
 */
@Controller
@RequestMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {
	static final Logger logger = LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private Repository repository;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<Contact> addContact(@RequestBody String json){
        logger.debug("add Contact method with request json: " + json);
        Contact contact;
		try {
            contact = new ObjectMapper().readValue(json, Contact.class);
            logger.debug("create contact entity from json string");
			repository.add(contact);
			logger.debug("add created Contact entity: " + contact.toString() + " to repository");
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            logger.error("error create Contact entity from json string: " + json + " with message: + " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DbServiceException e) {
			logger.error("error add Contact entity to repository wih message: " + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/contacts/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contact>> getContactByName(@PathVariable String name){
        logger.debug("get by name method with request name: " + name);
		List<Contact> contacts = null;
        try {
            contacts = repository.getByName(name);
			logger.debug("get Contact entity: " + contacts.toString() + " from DB");
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (DbServiceException e) {
            logger.error("error getting Contact entity from DB with message: + " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Contact> updateContact(@RequestBody String json){
        logger.debug("update Contact method with request json: " + json);
        Contact contact;
		try {
            contact = new ObjectMapper().readValue(json, Contact.class);
			logger.debug("create contact entity from json string");
            repository.update(contact);
			logger.debug("update created Contact entity: " + contact.toString() + " to repository");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (IOException e) {
            logger.error("error create Contact entity from json string: " + json + " with message: + " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DbServiceException e) {
			logger.error("error update Contact entity from DB with message: + " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Contact> deleteContact(@RequestBody String json){
        logger.debug("delete Contact method with request json: " + json);
		Contact contact;
        try {
            contact = new ObjectMapper().readValue(json, Contact.class);
            logger.debug("create contact entity from json string");
			repository.delete(contact);
            logger.debug("delete created Contact entity: " + contact.toString() + " from repository");
			return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            logger.error("error create Contact entity from json string: " + json + " with message: + " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DbServiceException e) {
			logger.error("error delete Contact entity from DB with message: + " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }
}
