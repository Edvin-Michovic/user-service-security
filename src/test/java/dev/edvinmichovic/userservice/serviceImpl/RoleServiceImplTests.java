package dev.edvinmichovic.userservice.serviceImpl;

import dev.edvinmichovic.userservice.model.Role;
import dev.edvinmichovic.userservice.repository.RoleRepository;
import dev.edvinmichovic.userservice.service.impl.RoleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoleServiceImplTests {
    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private RoleRepository roleRepository;

    private Role role;

    @BeforeEach
    public void setup() {
        role = new Role(1L, "Admin");
    }

    @DisplayName("Test - verify saveRole saves provided role")
    @Test
    public void testSaveRole() {
        // Given
        given(roleRepository.save(role)).willReturn(role);

        // When
        Role savedRole = roleService.saveRole(role);

        // Verify
        verify(roleRepository, times(1)).save(role);
        assertEquals(role, savedRole);
    }
}
