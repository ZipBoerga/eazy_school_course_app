package com.zip_boerga.eazy_school.repository;

import com.zip_boerga.eazy_school.model.Contact;

public interface ContactRepository {
    int saveContactMessage(Contact contact);
}
