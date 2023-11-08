package dev.edvinmichovic.userservice.serviceImpl;

import dev.edvinmichovic.userservice.model.AppUser;
import dev.edvinmichovic.userservice.model.Role;
import dev.edvinmichovic.userservice.repository.AppUserRepository;
import dev.edvinmichovic.userservice.repository.RoleRepository;
import dev.edvinmichovic.userservice.service.impl.RoleToAppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleToAppUserServiceImplTests {
    @InjectMocks
    private RoleToAppUserServiceImpl roleToAppUserService;

    @Mock
    private AppUserRepository appUserRepository;

    @Mock
    private RoleRepository roleRepository;

    private AppUser appUser;
    private Role role;
    private String username;
    private String roleName;

    @BeforeEach
    public void setup() {
        username = "edvinm";
        roleName = "Admin";
        appUser = mock(AppUser.class);
        role = mock(Role.class);
    }

    @DisplayName("Test - verify that addRoleToUser method assigns role to the user")
    @Test
    public void testAddRoleToUser() {
        // When
        when(appUserRepository.findByUsername(username)).thenReturn(appUser);
        when(roleRepository.findByName(roleName)).thenReturn(role);

        // Given
        given(appUser.getRoles()).willReturn(new ArrayList<>());

        // Then
        roleToAppUserService.addRoleToUser(username, roleName);

        // Verify
        verify(appUserRepository, times(1)).findByUsername(username);
        verify(roleRepository, times(1)).findByName(roleName);
        assertFalse(appUser.getRoles().isEmpty());
    }
}
