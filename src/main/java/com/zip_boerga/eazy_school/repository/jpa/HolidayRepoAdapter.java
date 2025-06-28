package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Holiday;
import com.zip_boerga.eazy_school.repository.interfaces.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public class HolidayRepoAdapter implements HolidaysRepository {
    private final JpaHolidayRepository jpaHolidayRepository;

    @Autowired
    public HolidayRepoAdapter(JpaHolidayRepository jpaHolidayRepository) {
        this.jpaHolidayRepository = jpaHolidayRepository;
    }

    @Override
    public List<Holiday> findAllHolidays() {
        return jpaHolidayRepository.findAll();
    }
}
