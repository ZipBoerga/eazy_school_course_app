package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JpaCourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByOrderByNameDesc();

    List<Course> findByOrderByName();
}
