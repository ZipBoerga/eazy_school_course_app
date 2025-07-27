package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.Address;
import com.zip_boerga.eazy_school.model.Profile;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
public class ProfileController {
    private final UserService userService;

    @Autowired
    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ModelAndView displayProfile(Model model, HttpSession session) {
        User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);

        var profileBuilder = Profile.builder()
                .name(user.getName())
                .email(user.getEmail())
                .mobileNumber(user.getMobileNumber());
        Address address = user.getAddress();
        if (address != null && address.getAddressId() > 0) {
            profileBuilder.address1(address.getAddress1())
                    .address2(address.getAddress2())
                    .city(address.getCity())
                    .state(address.getState())
                    .zipCode(address.getZipCode());
        }
        Profile profile = profileBuilder.build();

        ModelAndView modelAndView = new ModelAndView("profile");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping("/profile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session) {
        if (errors.hasErrors()) {
            return "profile";
        }
        User user = (User) session.getAttribute(Constants.LOGGED_IN_USER);
        User updatedUser = userService.updateUser(user, profile);
        session.setAttribute(Constants.LOGGED_IN_USER, updatedUser);
        return "redirect:/profile";
    }
}
