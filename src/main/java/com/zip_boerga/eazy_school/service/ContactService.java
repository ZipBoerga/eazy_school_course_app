package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.model.Contact;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;


@Getter
@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
public class ContactService {
    private int counter = 0;

    @PostConstruct
    public void postInit() {
        log.info("ContactService bean initialized: {}", this);
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        log.info(contact.toString());
        counter++;
        return isSaved;
    }

}
