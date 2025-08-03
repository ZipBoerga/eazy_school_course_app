package com.zip_boerga.eazy_school.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@EqualsAndHashCode(callSuper = true, exclude = {"students"})
@ToString(exclude = {"students"})
public class Course extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int courseId;

    private String name;

    private String fees;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(name = "user_course",
            joinColumns = {@JoinColumn(name = "course_id", referencedColumnName = "courseId")},
            inverseJoinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")}
    )    private Set<User> students = new HashSet<>();
}
