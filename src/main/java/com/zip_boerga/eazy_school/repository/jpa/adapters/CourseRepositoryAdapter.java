package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.Course;
import com.zip_boerga.eazy_school.repository.interfaces.CourseRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepositoryAdapter implements CourseRepository {
    private final JpaCourseRepository jpaCourseRepository;

    @Autowired
    public CourseRepositoryAdapter(JpaCourseRepository jpaCourseRepository) {
        this.jpaCourseRepository = jpaCourseRepository;
    }

    @Override
    public List<Course> findAll() {
        return this.jpaCourseRepository.findAll();
    }

    @Override
    public Course save(Course course) {
        return this.jpaCourseRepository.save(course);
    }

    @Override
    public Optional<Course> findById(int courseId) {
        return this.jpaCourseRepository.findById(courseId);
    }
}
