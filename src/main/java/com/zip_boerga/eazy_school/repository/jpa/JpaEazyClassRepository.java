package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.EazyClass;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository()
public interface JpaEazyClassRepository extends ListCrudRepository<EazyClass, Integer> {
}
