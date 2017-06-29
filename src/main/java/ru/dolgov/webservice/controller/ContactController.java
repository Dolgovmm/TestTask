package ru.dolgov.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
        Contact contact = null;
		try {
            contact = new ObjectMapper().readValue(json, Contact.class);
			repository.add(contact);
			if (logger.isDebugEnabled()) {
			    StringBuilder sb = new StringBuilder();
			    sb.append("Add contact {");
			    if (contact != null) {
                    sb.append(contact.toString());
                } else {
			        sb.append("null");
                }
                sb.append("} to repository");
                logger.debug(sb.toString());
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException e) {
            logErrorParseJson(json, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DbServiceException e) {
            logErrorDbService("add", contact, e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/contacts/{name}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<Contact>> getContactByName(@PathVariable String name){
		List<Contact> contacts = null;
        try {
            contacts = repository.getByName(name);
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("get contact list {");
                if (contacts != null) {
                    sb.append(contacts.toString());
                } else {
                    sb.append("null");
                }
                sb.append("} from repository");
                logger.debug(sb.toString());
            }
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        } catch (DbServiceException e) {
            if (logger.isErrorEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Error get list of contacts {");
                if (contacts != null) {
                    sb.append(contacts.toString());
                } else {
                    sb.append("null");
                }
                sb.append("} from repository with message: ");
                sb.append(e.getMessage());
                logger.error(sb.toString());
            }
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<Contact> updateContact(@RequestBody String json){
        Contact contact = null;
		try {
            contact = new ObjectMapper().readValue(json, Contact.class);
            repository.update(contact);
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Update contact {");
                if (contact != null) {
                    sb.append(contact.toString());
                } else {
                    sb.append("null");
                }
                sb.append("} to repository");
                logger.debug(sb.toString());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            logErrorParseJson(json, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DbServiceException e) {
            logErrorDbService("update", contact, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Contact> deleteContact(@RequestBody String json){
        Contact contact = null;
        try {
            contact = new ObjectMapper().readValue(json, Contact.class);
            repository.delete(contact);
            if (logger.isDebugEnabled()) {
                StringBuilder sb = new StringBuilder();
                sb.append("Delete contact {");
                if (contact != null) {
                    sb.append(contact.toString());
                } else {
                    sb.append("null");
                }
                sb.append("} from repository");
                logger.debug(sb.toString());
            }
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (IOException e) {
            logErrorParseJson(json, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (DbServiceException e) {
            logErrorDbService("delete", contact, e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private void logErrorParseJson(String json, IOException e) {
        if (logger.isErrorEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error create Contact entity from json string {");
            sb.append(json);
            sb.append("} with message: ");
            sb.append(e.getMessage());
            logger.error(sb.toString());
        }
    }

    private void logErrorDbService(String method, Contact contact, DbServiceException e) {
        if (logger.isErrorEnabled()) {
            StringBuilder sb = new StringBuilder();
            sb.append("Error ");
            sb.append(method);
            sb.append(" Contact entity {");
            if (contact != null) {
                sb.append(contact.toString());
            } else {
                sb.append("null");
            }
            sb.append("} to repository with message: ");
            sb.append(e.getMessage());
            logger.error(sb.toString());
        }
    }
}
