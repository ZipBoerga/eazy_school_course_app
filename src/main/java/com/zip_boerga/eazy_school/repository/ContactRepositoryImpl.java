package com.zip_boerga.eazy_school.repository;

import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

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

    @Override
    public List<Contact> findMessageByStatus(String status) {
        String query = "SELECT * FROM contact_message WHERE status = ?";
        return jdbcTemplate.query(query, new ContactRowMapper(), status);
    }

    @Override
    public int updateMessageStatus(int id, String updaterName, String messageStatus) {
        String query = "UPDATE contact_message SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
        return jdbcTemplate.update(query,
                messageStatus,
                updaterName,
                Timestamp.valueOf(LocalDateTime.now()),
                id);
    }
}
