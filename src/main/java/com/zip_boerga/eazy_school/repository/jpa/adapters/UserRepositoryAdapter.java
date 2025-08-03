package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class UserRepositoryAdapter implements UserRepository {
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserRepositoryAdapter(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User save(User user) {
        return jpaUserRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return jpaUserRepository.findByEmail(email).orElseThrow(() -> new
                UsernameNotFoundException(String.format("Registered user with given email (%s) is not found", email)));
    }

    @Override
    public User findById(int id) {
        return jpaUserRepository.findById(id).orElseThrow(() -> new
                UsernameNotFoundException(String.format("Registered user  with given id (%d) is not found", id)));
    }

    @Override
    public List<User> findByClassId(int id) {
        return jpaUserRepository.findUsersByClassId(id);
    }
}
