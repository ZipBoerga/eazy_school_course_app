package com.zip_boerga.eazy_school.controller;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
    public String closeMessage(@PathVariable int id) {
        contactService.updateMessageStatus(id, Constants.CLOSED);
        return "redirect:/messages";
    }

    @RequestMapping("/messages/page/{pageNum}")
    public String displayMessages(Model model, @PathVariable(name = "pageNum") int pageNum,
                                        @RequestParam("sortField") String sortField,
                                        @RequestParam("sortDir") String sortDir) {
        Page<Contact> page = contactService.getMessagesPaginated(Constants.OPEN, pageNum, sortDir, sortField);

        List<Contact> messages = page.getContent();
        model.addAttribute("currentPage", pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalMessages", page.getTotalElements());
        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
        model.addAttribute("messages", messages);
        return "messages";
    }

}
