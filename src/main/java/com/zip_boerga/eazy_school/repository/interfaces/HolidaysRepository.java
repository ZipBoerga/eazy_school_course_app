package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.Holiday;

import java.util.List;

public interface HolidaysRepository {
    List<Holiday> findAllHolidays();
}
