package com.zip_boerga.eazy_school.rest;

import com.zip_boerga.eazy_school.model.Contact;
import com.zip_boerga.eazy_school.service.ContactService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "/api/contact")
public class ContactRestController {
    private final ContactService contactService;

    @Autowired
    public ContactRestController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping("/messages")
    @ResponseBody
    public List<Contact> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactService.getMessages(status);
    }


    @PostMapping("/message")
    public ResponseEntity<Object> saveMessage(@RequestHeader("User-Agent") String userAgent,
                                              @Valid @RequestBody Contact contact,
                                              Errors errors) {
        log.info("Save message request on REST API comes from {}", userAgent);
        if (errors.hasErrors()) {
            log.error("Contact form validation while trying to save via REST API failed due to: {}", errors);
            List<String> messages = errors.getAllErrors()
                    .stream().map(ObjectError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(Map.of("errors", messages));

        }
        contactService.saveMessageDetails(contact);
        return ResponseEntity.status(HttpStatus.CREATED).build();
//                : ResponseEntity.internalServerError().build();
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable int id) {
        return contactService.deleteMessage(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @PatchMapping("/messages/{id}")
    public ResponseEntity<Object> updateMessage(RequestEntity<Contact> requestEntity, @PathVariable int id) {
        requestEntity.getHeaders().forEach((k, v) -> {
            log.info("Header {}: {}", k, v);
        });
        Contact contact = requestEntity.getBody();
        if (contact == null) {
            return ResponseEntity.badRequest().build();
        }
        contact.setContactId(id);
        Contact updatedContact = contactService.saveMessageDetails(contact);
        return ResponseEntity.ok(updatedContact);
    }

}
