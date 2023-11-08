package dev.edvinmichovic.userservice.serviceImpl;

import dev.edvinmichovic.userservice.model.AppUser;
import dev.edvinmichovic.userservice.repository.AppUserRepository;
import dev.edvinmichovic.userservice.service.impl.AppUserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceImplTests {
    @InjectMocks
    private AppUserServiceImpl appUserService;

    @Mock
    private AppUserRepository appUserRepository;

    private AppUser appUser;

    @BeforeEach
    public void setup() {
        appUser = new AppUser(1L, "Edvin", "Michovic", "edvinm", "1234567", Collections.emptyList());
    }

    @DisplayName("Test - verify saveUser method saves provided user")
    @Test
    public void testSaveUser() {
        // Given
        given(appUserRepository.save(appUser)).willReturn(appUser);

        // When
        AppUser savedUser = appUserService.saveUser(appUser);

        // Verify
        verify(appUserRepository, times(1)).save(appUser);
        assertEquals(savedUser, appUser);
    }

    @DisplayName("Test - verify getUser method provides correct user")
    @Test
    public void testGetUser() {
        // Given
        given(appUserRepository.findByUsername("edvinm")).willReturn(appUser);

        // When
        AppUser foundUser = appUserService.getUser("edvinm");

        // Verify
        verify(appUserRepository, times(1)).findByUsername("edvinm");
        assertEquals(appUser.getId(), foundUser.getId());
        assertEquals(appUser.getName(), foundUser.getName());
        assertEquals(appUser.getSurname(), foundUser.getSurname());
        assertEquals(appUser.getUsername(), foundUser.getUsername());
        assertEquals(appUser.getPassword(), foundUser.getPassword());
        assertTrue(appUser.getRoles().equals(foundUser.getRoles()));
    }

    @DisplayName("Test - verify getAllUsers method provides a correct list of users")
    @Test
    public void testGetAllUsers() {
        AppUser secondAppUser = new AppUser(2L, "Derek", "Mangusto", "d.mangusto", "123", Collections.emptyList());

        // Given
        given(appUserRepository.findAll()).willReturn(List.of(appUser, secondAppUser));

        // When
        List<AppUser> appUserList = appUserService.getAllUsers();

        // Verify
        verify(appUserRepository, times(1)).findAll();
        assertFalse(appUserList.isEmpty());
        assertEquals(2, appUserList.size());
        assertTrue(appUserList.get(0).getId().equals(1L));
        assertTrue(appUserList.get(1).getId().equals(2L));
    }
}
