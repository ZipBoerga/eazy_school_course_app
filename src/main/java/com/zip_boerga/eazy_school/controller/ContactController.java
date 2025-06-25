package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.model.Constants;
import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@Controller
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/contact")
    public String displayControllerPage(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact";
    }

    @PostMapping("/contact")
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
        log.info("Current ContactService instance is {}", this.contactService);
        if (errors.hasErrors()) {
            log.error("Contact form validation failed due to: {}", errors);
            return "contact";
        }
        contactService.saveMessageDetails(contact);
        return "redirect:/contact";
    }

    @GetMapping("/messages")
    public String displayMessages(Model model) {
        List<Contact> contactMessages = contactService.getMessages(Constants.OPEN);
        model.addAttribute("messages", contactMessages);
        return "messages";
    }

    @PostMapping("/messages/{id}/close")
    public String closeMessage(@PathVariable int id, Authentication authentication) {
        String updaterName = authentication.getName();
        contactService.updateMessageStatus(id, updaterName, Constants.CLOSED);
        return "redirect:/messages";
    }
}
