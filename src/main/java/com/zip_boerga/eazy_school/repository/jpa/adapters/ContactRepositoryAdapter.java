package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.interfaces.ContactRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ContactRepositoryAdapter implements ContactRepository {
    private final JpaContactRepository jpaContactRepository;

    @Autowired
    public ContactRepositoryAdapter(JpaContactRepository jpaContactRepository) {
        this.jpaContactRepository = jpaContactRepository;
    }

    @Override
    public int saveContactMessage(Contact contact) {
        Contact savedContact = jpaContactRepository.save(contact);
        return savedContact.getContactId();
    }

    @Override
    public List<Contact> findMessageByStatus(String status) {
        return jpaContactRepository.findByStatus(status);
    }

    @Override
    public int updateMessageStatus(int id, String status) {
        Optional<Contact> contactWrap = jpaContactRepository.findById(id);
        if (contactWrap.isEmpty()) {
            return 0;
        }

        Contact contact = contactWrap.get();
        contact.setStatus(status);

        Contact updatedContact = jpaContactRepository.save(contactWrap.get());

        return updatedContact.getContactId();
    }
}
