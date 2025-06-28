package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Contact;
import org.springframework.context.annotation.Primary;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface JpaContactRepository extends ListCrudRepository<Contact, Integer> {
    List<Contact> findByStatus(String status);
}
