package com.zip_boerga.eazy_school.repository;

import com.zip_boerga.eazy_school.model.Contact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepositoryImpl implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveContactMessage(Contact contact) {
        String query = "INSERT INTO contact_message (name, mobile_num, email, subject, message, status, " +
                "created_at, created_by) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        return jdbcTemplate.update(query,
                contact.getName(),
                contact.getMobileNum(),
                contact.getEmail(),
                contact.getSubject(),
                contact.getMessage(),
                contact.getStatus(),
                contact.getCreatedAt(),
                contact.getCreatedBy());
    }
}
