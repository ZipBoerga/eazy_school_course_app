package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.interfaces.ContactRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

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
    public Contact saveMessageDetails(Contact contact) {
        contact.setStatus(Constants.OPEN);
        return contactRepository.saveContactMessage(contact);
    }

    public List<Contact> getMessages(String status) {
        return contactRepository.findByStatus(status);
    }

    public Page<Contact> getMessagesPaginated(String status, int pageNum, String sortDir, String sortField) {
        int pageSize = 5;
        return contactRepository.findByStatus(status, pageNum, pageSize, sortDir, sortField);
    }

    public boolean updateMessageStatus(int id, String status) {
        int result = contactRepository.updateMessageStatus(id, status);
        return result != 0;
    }

    public boolean deleteMessage(int id) {
        return contactRepository.deleteById(id);
    }
}
