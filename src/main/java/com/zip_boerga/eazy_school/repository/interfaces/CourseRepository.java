package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    List<Course> findAll();
    Course save(Course course);
    Optional<Course> findById(int courseId);
}
