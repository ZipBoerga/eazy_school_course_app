package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.model.Constants;
import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.ContactRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


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
        contact.setStatus(Constants.OPEN);
        contact.setCreatedBy(Constants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMessage(contact);
        if (result > 0) {
            isSaved = true;
        }
        return isSaved;
    }

    public List<Contact> getMessages(String status) {
        return contactRepository.findMessageByStatus(status);
    }

    public boolean updateMessageStatus(int id, String status) {
        int changedRowsCount = contactRepository.updateMessageStatus(id, status);
        return changedRowsCount != 0;
    }
}
