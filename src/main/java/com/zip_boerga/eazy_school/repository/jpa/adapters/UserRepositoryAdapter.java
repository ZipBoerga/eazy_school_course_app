package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
        return jpaUserRepository.readByEmail(email);
    }
}
