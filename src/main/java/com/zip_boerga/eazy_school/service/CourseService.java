package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.model.Course;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.CourseRepository;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    public List<Course> getAllCourses() {
        return this.courseRepository.findAll();
    }

    public Course saveCourse(Course course) {
        return this.courseRepository.save(course);
    }

    public Set<User> getCourseStudents(int courseId) {
        Course course = this.getCourse(courseId);
        return course.getStudents();
    }

    public Course getCourse(int courseId) {
        return this.courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("The course not found!"));
    }

    @Transactional
    public Course addStudentToCourse(String studentEmail, int courseId) {
        Course course = this.getCourse(courseId);
        User user = this.userRepository.findByEmail(studentEmail);

        course.getStudents().add(user);
        user.getCourses().add(course);

        return this.courseRepository.save(course);
    }

    @Transactional
    public Course removeStudentFromCourse(int studentId, int courseId) {
        Course course = this.getCourse(courseId);
        User user = this.userRepository.findById(studentId);

        course.getStudents().remove(user);
        user.getCourses().remove(course);

        return this.courseRepository.save(course);
    }
}
