package com.zip_boerga.eazy_school.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

// TODO: protect.
@RestController
@RequestMapping("api/debug")
public class DebugController {

    private final UserDetailsManager userDetailsManager;

    public DebugController(UserDetailsManager userDetailsManager) {
        this.userDetailsManager = userDetailsManager;
    }

    @GetMapping({"/admin", "/admin/"})
    public Map<String, String> getAdminFromRam() {
        UserDetails user = userDetailsManager.loadUserByUsername("admin");
        Map<String, String> userDetails = new HashMap<>();
        userDetails.put("username", user.getUsername());
        userDetails.put("password", user.getPassword());
        return userDetails;
    }

}
