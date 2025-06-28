package com.zip_boerga.eazy_school.repository.jdbc.rowmappers;

import com.zip_boerga.eazy_school.model.Holiday;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;


public class HolidayRowMapper implements RowMapper<Holiday> {
    @Override
    public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setDay(rs.getString("day"));
        holiday.setReason(rs.getString("reason"));
        holiday.setType(Holiday.Type.valueOf(rs.getString("type")));
        RowMapperUtils.populateAuditFields(holiday, rs);
        return holiday;
    }
}
