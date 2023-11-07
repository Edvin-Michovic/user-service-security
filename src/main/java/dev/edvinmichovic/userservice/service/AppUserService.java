package dev.edvinmichovic.userservice.service;

import dev.edvinmichovic.userservice.model.AppUser;

import java.util.List;

public interface AppUserService {
    AppUser saveUser(AppUser appUser);
    AppUser getUser(String username);
    List<AppUser> getAllUsers();
}
