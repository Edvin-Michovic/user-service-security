package dev.edvinmichovic.userservice.service.impl;

import dev.edvinmichovic.userservice.model.Role;
import dev.edvinmichovic.userservice.repository.RoleRepository;
import dev.edvinmichovic.userservice.service.RoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {} to the Database.", role.getName());
        return roleRepository.save(role);
    }
}
