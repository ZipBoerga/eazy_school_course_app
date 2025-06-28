package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.Contact;

import java.util.List;

public interface ContactRepository {
    int saveContactMessage(Contact contact);
    List<Contact> findMessageByStatus(String status);
    int updateMessageStatus(int id, String status);
}
