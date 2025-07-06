package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.Role;

public interface RoleRepository {
    Role getByRoleName(String roleName);
}
