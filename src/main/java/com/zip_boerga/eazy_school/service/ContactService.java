package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.controller.ContactController;
import com.zip_boerga.eazy_school.model.Contact;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class ContactService {
    private final static Logger log = Logger.getLogger(ContactController.class.getName());

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        log.info(contact.toString());
        return isSaved;
    }
}
