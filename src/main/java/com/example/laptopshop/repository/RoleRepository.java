package com.example.laptopshop.repository;

import org.springframework.stereotype.Repository;
import com.example.laptopshop.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
