package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.Course;
import com.zip_boerga.eazy_school.model.EazyClass;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.service.ClassesService;
import com.zip_boerga.eazy_school.service.CourseService;
import com.zip_boerga.eazy_school.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {
    private final ClassesService classesService;
    private final UserService userService;
    private final CourseService coursesService;

    @Autowired
    public AdminController(ClassesService classesService, UserService userService, CourseService coursesService) {
        this.classesService = classesService;
        this.userService = userService;
        this.coursesService = coursesService;
    }

    @GetMapping("/classes")
    public ModelAndView displayClasses() {
        List<EazyClass> eazyClasses = this.classesService.getClasses();
        ModelAndView modelAndView = new ModelAndView("classes");
        modelAndView.addObject("eazyClass", new EazyClass());
        modelAndView.addObject("eazyClasses", eazyClasses);
        return modelAndView;
    }

    @PostMapping("/class")
    public ModelAndView addClass(@ModelAttribute("eazyClass") EazyClass eazyClass) {
        this.classesService.addClass(eazyClass);
        return new ModelAndView("redirect:/admin/classes");
    }

    @PostMapping("/deleteClass")
    public ModelAndView deleteClass(@RequestParam int classId) {
        this.classesService.deleteClass(classId);
        return new ModelAndView("redirect:/admin/classes");
    }

    @Transactional()
    @GetMapping("/students")
    public ModelAndView displayClassStudents(@RequestParam int classId, HttpSession session,
                                         @RequestParam(value = "error", required = false) String error, Model model) {
        Optional<EazyClass> eazyClassWrapper = this.classesService.getClassById(classId);
        if (eazyClassWrapper.isEmpty()) {
            throw new RuntimeException("No class with such ID has been returned.");
        }
        EazyClass eazyClass = eazyClassWrapper.get();

        List<User> students = this.userService.findByClassId(classId);

        ModelAndView modelAndView = new ModelAndView("students");
        modelAndView.addObject("eazyClass", eazyClass);
        modelAndView.addObject("student", new User());
        modelAndView.addObject("students", students);

        session.setAttribute("eazyClass", eazyClass);

        if (error != null) {
            modelAndView.addObject("errorMessage", "Invalid user email entered");
        }

        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(@ModelAttribute("student") User student, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();

        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClass");
        int classId = eazyClass.getClassId();
        // Not a good design suggested by a course teacher
        try {
            this.classesService.addStudentByEmail(student.getEmail(), classId);
            modelAndView.setViewName(String.format("redirect:/admin/students?classId=%d", classId));
        } catch (UsernameNotFoundException e) {
            modelAndView.setViewName(String.format("redirect:/admin/students?classId=%d&error=true", classId));
        }
        return modelAndView;
    }

    @PostMapping("/deleteStudent") // delete student FROM CLASS. another poor semantic example by teacher
    public ModelAndView deleteStudent(@ModelAttribute("studentId") int studentId, HttpSession httpSession) {
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClass");
        int classId = eazyClass.getClassId();
        EazyClass updatedClass = this.classesService.deleteStudentFromClass(studentId, classId);
        httpSession.setAttribute(Constants.EAZY_CLASS, updatedClass);
        return new ModelAndView(String.format("redirect:/admin/students?classId=%d", classId));
    }

    @GetMapping("/courses")
    public ModelAndView displayCourses() {
        List<Course> courses = this.coursesService.getAllCourses();
        ModelAndView modelAndView = new ModelAndView("courses_admin");
        modelAndView.addObject("courses", courses);
        modelAndView.addObject(Constants.COURSE, new Course());
        return modelAndView;
    }

    @PostMapping("/course")
    public String addNewCourse(@ModelAttribute("course") Course course) {
        this.coursesService.saveCourse(course);
        return "redirect:/admin/courses";
    }

    @GetMapping("/courses/students")
    public ModelAndView displayCourseStudents(@RequestParam int id, HttpSession session,
                                              @RequestParam(required = false) String error) {
        ModelAndView modelAndView = new ModelAndView("course_students");
        Course course = this.coursesService.getCourse(id);
        modelAndView.addObject(Constants.COURSE, course);
        modelAndView.addObject("student", new User());
        session.setAttribute(Constants.COURSE, course);

        if (error != null) {
            modelAndView.addObject("errorMessage", "Invalid email entered.");
        }

        return modelAndView;
    }

    @PostMapping("/course/student")
    public String addStudentToCourse(@ModelAttribute("student") User student, HttpSession session) {
        Course course = (Course) session.getAttribute(Constants.COURSE);
        String redirectUrl = String.format("redirect:/admin/courses/students?id=%d", course.getCourseId());
        try {
            Course updatedCourse = this.coursesService.addStudentToCourse(student.getEmail(), course.getCourseId());
            session.setAttribute(Constants.COURSE, updatedCourse);
        } catch (UsernameNotFoundException e) {
            redirectUrl += "&error=true";
        }
        return redirectUrl;
    }

    @PostMapping("/course/removeStudent")
    public String removeStudentFromCourse(@RequestParam int studentId, HttpSession session) {
        Course course = (Course) session.getAttribute(Constants.COURSE);
        Course updatedCourse = this.coursesService.removeStudentFromCourse(studentId, course.getCourseId());
        session.setAttribute(Constants.COURSE, updatedCourse);
        return String.format("redirect:/admin/courses/students?id=%d", course.getCourseId());
    }
}
