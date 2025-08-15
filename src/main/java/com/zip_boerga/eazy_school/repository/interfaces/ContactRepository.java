package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.Contact;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ContactRepository {
    Contact saveContactMessage(Contact contact);
    List<Contact> findByStatus(String status);
    int updateMessageStatus(int id, String status);
    Page<Contact> findByStatus(String status, int pageNum, int pageSize, String sortDir, String sortField);
    boolean deleteById(int id);
}
