package dev.edvinmichovic.userservice.service;

public interface RoleToAppUserService {
    void addRoleToUser(String username, String roleName);
}
