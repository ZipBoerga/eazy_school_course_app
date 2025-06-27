package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.model.Holiday;
import com.zip_boerga.eazy_school.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class HolidayService {
    public final HolidaysRepository holidaysRepository;

    @Autowired
    public HolidayService(HolidaysRepository holidaysRepository) {
        this.holidaysRepository = holidaysRepository;
    }

    public List<Holiday> getHolidays() {
        return holidaysRepository.findAllHolidays();
    }

}
