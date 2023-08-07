package com.springsecurity.repository;

import com.springsecurity.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findUserById(Long id);

}
