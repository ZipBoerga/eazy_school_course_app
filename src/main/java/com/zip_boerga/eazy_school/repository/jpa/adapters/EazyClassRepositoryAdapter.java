package com.zip_boerga.eazy_school.repository.jpa.adapters;

import com.zip_boerga.eazy_school.model.EazyClass;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.EazyClassRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaEazyClassRepository;
import com.zip_boerga.eazy_school.repository.jpa.JpaUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public class EazyClassRepositoryAdapter implements EazyClassRepository {
    private final JpaEazyClassRepository jpaEazyClassRepository;

    @Autowired
    public EazyClassRepositoryAdapter(JpaEazyClassRepository jpaEazyClassRepository) {
        this.jpaEazyClassRepository = jpaEazyClassRepository;
    }

    @Override
    public int saveClass(EazyClass eazyClass) {
        EazyClass savedClass = this.jpaEazyClassRepository.save(eazyClass);
        return savedClass.getClassId();
    }

    @Override
    public List<EazyClass> getAllClasses() {
        return this.jpaEazyClassRepository.findAll();
    }

    @Override
    public void deleteClass(int id) {
        jpaEazyClassRepository.deleteById(id);
    }

    // Throwing concurrent exception when accessing student
    @Override
    public List<User> getClassStudents(int id) {
        return this.jpaEazyClassRepository.findById(id)
                .map(EazyClass::getStudents)
                .orElse(Collections.emptyList());
    }

    @Override
    public Optional<EazyClass> findById(int id) {
        return this.jpaEazyClassRepository.findById(id);
    }
}
