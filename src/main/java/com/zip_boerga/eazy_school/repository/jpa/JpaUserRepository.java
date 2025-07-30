package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface JpaUserRepository extends ListCrudRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT * FROM user WHERE class_id = :classId", nativeQuery = true)
    List<User> findUsersByClassId(@Param("classId") int classId);}
