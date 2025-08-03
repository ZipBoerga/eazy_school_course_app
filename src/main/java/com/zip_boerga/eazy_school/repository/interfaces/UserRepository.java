package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(User user);
    User findByEmail(String email) throws UsernameNotFoundException;
    User findById(int id) throws UsernameNotFoundException;
    List<User> findByClassId(int id);
}
