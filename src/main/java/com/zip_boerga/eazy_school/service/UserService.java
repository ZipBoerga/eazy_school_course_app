package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.Role;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.RoleRepository;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public boolean registerUser(User user) {
        Role role = roleRepository.getByRoleName(Constants.STUDENT);
        user.setRole(role);
        user = userRepository.save(user);
        return user != null && user.getUserId() > 0;
    }

}
