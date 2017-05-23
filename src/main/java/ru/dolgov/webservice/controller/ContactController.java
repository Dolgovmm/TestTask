package ru.dolgov.webservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.dolgov.webservice.entity.Contact;
import ru.dolgov.webservice.repository.Repository;

/**
 * Created by Михалыч on 23.05.2017.
 */
@Controller
@RequestMapping(value = "/contact", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContactController {

    @Autowired
    private Repository repository;

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody void addContact(@RequestBody String json){
        
    }
}
