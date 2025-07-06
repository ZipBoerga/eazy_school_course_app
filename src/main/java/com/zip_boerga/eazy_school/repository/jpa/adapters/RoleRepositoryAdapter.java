package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.Role;
import com.zip_boerga.eazy_school.repository.interfaces.RoleRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleRepositoryAdapter implements RoleRepository {
    private final JpaRoleRepository jpaRoleRepository;

    @Autowired
    public RoleRepositoryAdapter(JpaRoleRepository jpaRoleRepository) {
        this.jpaRoleRepository = jpaRoleRepository;
    }

    @Override
    public Role getByRoleName(String roleName) {
        return jpaRoleRepository.getByRoleName(roleName);
    }
}
