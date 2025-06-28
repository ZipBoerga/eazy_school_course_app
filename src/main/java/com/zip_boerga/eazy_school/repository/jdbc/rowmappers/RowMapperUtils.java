package com.zip_boerga.eazy_school.repository.jdbc.rowmappers;

import com.zip_boerga.eazy_school.model.BaseEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RowMapperUtils {
     public static void populateAuditFields(BaseEntity entity, ResultSet rs) throws SQLException {
         entity.setCreatedAt(rs.getTimestamp("CREATED_AT").toLocalDateTime());
         entity.setCreatedBy(rs.getString("CREATED_BY"));

         if (null != rs.getTimestamp("UPDATED_AT")) {
             entity.setUpdatedAt(rs.getTimestamp("UPDATED_AT").toLocalDateTime());
         }

         entity.setUpdatedBy(rs.getString("UPDATED_BY"));
     }
}
