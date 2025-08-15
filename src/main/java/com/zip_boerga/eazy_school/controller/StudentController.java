package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.User;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("student")
public class StudentController {

    @GetMapping("/courses")
    public ModelAndView displayCourses(HttpSession session) {
        User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
        ModelAndView modelAndView = new ModelAndView("courses_enrolled");
        modelAndView.addObject("user", user);
        return modelAndView;
    }
}
