package com.zip_boerga.eazy_school.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class LoginController {
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String displayLoginPage(
            @RequestParam(value = "error", required = false) String error,
            @RequestParam(value = "logout", required = false) String logout,
            Model model
    ) {
        String message = null;

        if (error != null) {
            message = "Username or password in incorrect.";
        }
        if (logout != null) {
            message = "You have been successfully logged out.";
        }

        model.addAttribute("message", message);
        return "login";
    }
}
