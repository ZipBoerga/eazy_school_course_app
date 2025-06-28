package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Holiday;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaHolidayRepository extends ListCrudRepository<Holiday, String> {}
