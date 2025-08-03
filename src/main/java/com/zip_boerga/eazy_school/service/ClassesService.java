package com.zip_boerga.eazy_school.service;

import com.zip_boerga.eazy_school.model.EazyClass;
import com.zip_boerga.eazy_school.model.User;
import com.zip_boerga.eazy_school.repository.interfaces.EazyClassRepository;
import com.zip_boerga.eazy_school.repository.interfaces.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClassesService {
    private final EazyClassRepository eazyClassRepository;
    private final UserRepository userRepository;

    @Autowired
    public ClassesService(EazyClassRepository eazyClassRepository, UserRepository userRepository) {
        this.eazyClassRepository = eazyClassRepository;
        this.userRepository = userRepository;
    }

    public void addClass(EazyClass eazyClass) {
        this.eazyClassRepository.saveClass(eazyClass);
    }

    public List<EazyClass> getClasses() {
        return this.eazyClassRepository.getAllClasses();
    }

    public Optional<EazyClass> getClassById(int id) {
        return this.eazyClassRepository.findById(id);
    }

    @Transactional
    public void deleteClass(int id) {
        Optional<EazyClass> eazyClass = this.eazyClassRepository.findById(id);
        if (eazyClass.isEmpty()) {
            return;
        }
        this.eazyClassRepository.deleteClass(id);
        for (User user : eazyClass.get().getStudents()) {
            user.setEazyClass(null);
            userRepository.save(user);
        }
    }

    @Transactional
    public EazyClass deleteStudentFromClass(int userId, int classId) {
        User student = userRepository.findById(userId);
        EazyClass eazyClass = eazyClassRepository.findById(classId).orElseThrow(() -> new
                RuntimeException(String.format("Class with given ID %d is not found", (classId))));

        eazyClass.getStudents().remove(student);
        student.setEazyClass(null);
        userRepository.save(student);

        return eazyClass;
    }

    @Transactional
    public void addStudentByEmail(String email, int classId) {
        User student = userRepository.findByEmail(email);
        EazyClass eazyClass = eazyClassRepository.findById(classId).orElseThrow(() -> new
                RuntimeException(String.format("Class with given ID %d is not found", (classId))));
        student.setEazyClass(eazyClass);
        userRepository.save(student);
    }

}
