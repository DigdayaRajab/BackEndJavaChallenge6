package com.binar.challenge5.repositories;

import com.binar.challenge5.entities.Role;
import com.binar.challenge5.model.enumerations.ERoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERoles name);
}
