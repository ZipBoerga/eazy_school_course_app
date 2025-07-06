package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Role;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaRoleRepository extends ListCrudRepository<Role, Integer> {
    Role getByRoleName(String name);
}

