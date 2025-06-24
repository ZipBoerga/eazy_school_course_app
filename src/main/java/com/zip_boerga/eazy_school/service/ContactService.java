package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.model.Constants;
import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.ContactRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Slf4j
@Service
public class ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @PostConstruct
    public void postInit() {
        log.info("ContactService bean initialized: {}", this);
    }

    // i do not like semantics
    public boolean saveMessageDetails(Contact contact) {
        boolean isSaved = false;
        contact.setStatus(Constants.OPEN.toString());
        contact.setCreatedBy(Constants.ANONYMOUS.toString());
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMessage(contact);
        if (result > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
