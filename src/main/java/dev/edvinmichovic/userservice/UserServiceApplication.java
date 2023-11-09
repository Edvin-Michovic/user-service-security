package dev.edvinmichovic.userservice;

import dev.edvinmichovic.userservice.model.AppUser;
import dev.edvinmichovic.userservice.model.Role;
import dev.edvinmichovic.userservice.service.AppUserService;
import dev.edvinmichovic.userservice.service.RoleService;
import dev.edvinmichovic.userservice.service.RoleToAppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@SpringBootApplication
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner run(RoleService roleService, AppUserService appUserService, RoleToAppUserService roleToAppUserService) {
		return args -> {
			roleService.saveRole(new Role(null, "ROLE_USER"));
			roleService.saveRole(new Role(null, "ROLE_MANAGER"));
			roleService.saveRole(new Role(null, "ROLE_ADMIN"));
			roleService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));

			appUserService.saveUser(new AppUser(null, "Edvin", "Michovic", "edvinmichovic@gmail.com", "12345", Collections.emptyList()));
			appUserService.saveUser(new AppUser(null, "John", "Doe", "johndoe@gmail.com", "12345", Collections.emptyList()));
			appUserService.saveUser(new AppUser(null, "Will", "Smith", "will.smith@gmail.com", "12345", Collections.emptyList()));
			appUserService.saveUser(new AppUser(null, "Johnny", "Pixels", "johnnypixels@gmail.com", "12345", Collections.emptyList()));

			roleToAppUserService.addRoleToUser("edvinmichovic@gmail.com", "ROLE_SUPER_ADMIN");
			roleToAppUserService.addRoleToUser("edvinmichovic@gmail.com", "ROLE_ADMIN");
			roleToAppUserService.addRoleToUser("will.smith@gmail.com", "ROLE_USER");
			roleToAppUserService.addRoleToUser("johnnypixels@gmail.com", "ROLE_MANAGER");
		};
	}

}
