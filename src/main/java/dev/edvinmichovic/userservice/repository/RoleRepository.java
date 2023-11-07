package dev.edvinmichovic.userservice.repository;

import dev.edvinmichovic.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
