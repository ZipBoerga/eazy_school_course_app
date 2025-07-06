package com.zip_boerga.eazy_school.repository.jpa;

import com.zip_boerga.eazy_school.model.Address;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaAddressRepository extends ListCrudRepository<Address, Integer> { }
