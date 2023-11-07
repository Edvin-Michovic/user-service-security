package dev.edvinmichovic.userservice.service.impl;

import dev.edvinmichovic.userservice.model.AppUser;
import dev.edvinmichovic.userservice.model.Role;
import dev.edvinmichovic.userservice.repository.AppUserRepository;
import dev.edvinmichovic.userservice.repository.RoleRepository;
import dev.edvinmichovic.userservice.service.RoleToAppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class RoleToAppUserServiceImpl implements RoleToAppUserService {

    private final AppUserRepository appUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public void addRoleToUser(String username, String roleName) {
        log.info("Adding role {} to user {}.", roleName, username);
        AppUser appUser = appUserRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        appUser.getRoles().add(role);

    }
}
