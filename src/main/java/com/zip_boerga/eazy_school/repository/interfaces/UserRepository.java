package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    // should probably not be Optional, throwing an error in Adapter if not found for both methods
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    List<User> findByClassId(int id);
}
