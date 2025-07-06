package com.zip_boerga.eazy_school.model;

import lombok.Data;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roleId;
    private String roleName;
}