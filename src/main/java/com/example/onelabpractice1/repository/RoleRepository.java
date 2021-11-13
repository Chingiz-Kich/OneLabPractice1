package com.example.onelabpractice1.repository;

import com.example.onelabpractice1.models.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByName(String name);

    boolean existsByName(String name);
}
