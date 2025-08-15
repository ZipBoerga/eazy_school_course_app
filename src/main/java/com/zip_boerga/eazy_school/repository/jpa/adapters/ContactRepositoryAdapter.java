package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.interfaces.ContactRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Contact saveContactMessage(Contact contact) {
        return jpaContactRepository.save(contact);
    }

    @Override
    public List<Contact> findByStatus(String status) {
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

    @Override
    public Page<Contact> findByStatus(String status, int pageNum, int pageSize, String sortDir, String sortField) {
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        return jpaContactRepository.findByStatus(status, pageable);
    }

    @Override
    public boolean deleteById(int id) {
        try {
            jpaContactRepository.deleteById(id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }
}
