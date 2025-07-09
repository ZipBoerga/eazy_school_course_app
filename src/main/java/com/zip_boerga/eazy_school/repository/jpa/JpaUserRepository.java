package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.User;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaUserRepository extends ListCrudRepository<User, Integer> {
    User readByEmail(String email);
}
