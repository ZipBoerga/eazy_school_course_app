package com.zip_boerga.eazy_school.repository;

import com.zip_boerga.eazy_school.model.Holiday;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HolidaysRepositoryImpl implements HolidaysRepository {
    private final JdbcTemplate jdbcTemplate;

    public HolidaysRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Holiday> findAllHolidays() {
        String query = "SELECT * FROM holidays";
        return jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Holiday.class));
    }
}
