package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Course;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaCourseRepository extends ListCrudRepository<Course, Integer> { }
