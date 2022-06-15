package com.example.main.repository;

import com.example.main.entity.secutity.ERole;
import com.example.main.entity.secutity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
