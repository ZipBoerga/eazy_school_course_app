package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.Constants;
import com.zip_boerga.eazy_school.model.Address;
import com.zip_boerga.eazy_school.model.Profile;
import com.zip_boerga.eazy_school.model.Role;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.RoleRepository;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean registerUser(User user) {
        Role role = roleRepository.getByRoleName(Constants.STUDENT);
        user.setRole(role);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setConfirmPassword(null);

        user = userRepository.save(user);
        return user != null && user.getUserId() > 0;
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    public User updateUser(User currentUser, Profile currentProfile) {
        currentUser.setName(currentProfile.getName());
        currentUser.setEmail(currentProfile.getEmail());
        currentUser.setMobileNumber(currentProfile.getMobileNumber());
        if (currentUser.getAddress() == null || !(currentUser.getAddress().getAddressId()>0)){
            currentUser.setAddress(new Address());
        }
        currentUser.getAddress().setAddress1(currentProfile.getAddress1());
        currentUser.getAddress().setAddress2(currentProfile.getAddress2());
        currentUser.getAddress().setCity(currentProfile.getCity());
        currentUser.getAddress().setState(currentProfile.getState());
        currentUser.getAddress().setZipCode(currentProfile.getZipCode());
        return userRepository.save(currentUser);
    }

    public List<User> findByClassId(int classId) {
        return userRepository.findByClassId(classId);

    }

}
