package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Contact;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Primary
public interface JpaContactRepository extends ListCrudRepository<Contact, Integer>,
        PagingAndSortingRepository<Contact, Integer> {
    List<Contact> findByStatus(String status);

    Page<Contact> findByStatus(String status, Pageable pageable);
}
