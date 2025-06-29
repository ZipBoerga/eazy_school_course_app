package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.model.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {
    @GetMapping("/register")
    public String displayRegisterPage(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/user")
    public String registerUser(@Valid @ModelAttribute("user") User user, Errors errors) {
        return errors.hasErrors() ? "register" : "redirect:/login?register=true";
    }
}
