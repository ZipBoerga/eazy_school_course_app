package com.zip_boerga.eazy_school.repository.jdbc;

import com.zip_boerga.eazy_school.aspects.audit.AuditContext;
import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.repository.interfaces.ContactRepository;
import com.zip_boerga.eazy_school.repository.jdbc.rowmappers.ContactRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JdbcContactRepository implements ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int saveContactMessage(Contact contact) {
        String query = "INSERT INTO contact_messages (name, mobile_num, email, subject, message, status, " +
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
        String query = "SELECT * FROM contact_messages WHERE status = ?";
        return jdbcTemplate.query(query, new ContactRowMapper(), status);
    }

    @Override
    public int updateMessageStatus(int id, String messageStatus) {
        String username = AuditContext.get();
        String query = "UPDATE contact_messages SET STATUS = ?, UPDATED_BY = ?, UPDATED_AT = ? WHERE CONTACT_ID = ?";
        return jdbcTemplate.update(query,
                messageStatus,
                username,
                Timestamp.valueOf(LocalDateTime.now()),
                id);
    }
}
