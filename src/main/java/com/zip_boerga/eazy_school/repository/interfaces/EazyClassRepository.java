package com.zip_boerga.eazy_school.repository.interfaces;

import com.zip_boerga.eazy_school.model.EazyClass;
import com.zip_boerga.eazy_school.model.User;

import java.util.List;
import java.util.Optional;

public interface EazyClassRepository {
    int saveClass(EazyClass eazyClass);
    List<EazyClass> getAllClasses();
    void deleteClass(int id);
    List<User> getClassStudents(int id);
    Optional<EazyClass> findById(int id);
}
