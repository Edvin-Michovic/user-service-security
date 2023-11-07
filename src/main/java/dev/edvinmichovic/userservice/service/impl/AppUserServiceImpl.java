package dev.edvinmichovic.userservice.service.impl;

import dev.edvinmichovic.userservice.model.AppUser;
import dev.edvinmichovic.userservice.repository.AppUserRepository;
import dev.edvinmichovic.userservice.service.AppUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppUserServiceImpl implements AppUserService {
    private final AppUserRepository appUserRepository;

    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("Saving new user {} to the Database.", appUser.getName());
        return appUserRepository.save(appUser);
    }

    @Override
    public AppUser getUser(String username) {
        log.info("Fetching user {}.", username);
        return appUserRepository.findByUsername(username);
    }

    @Override
    public List<AppUser> getAllUsers() {
        log.info("Fetching all users.");
        return appUserRepository.findAll();
    }
}
