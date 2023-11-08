package dev.edvinmichovic.userservice.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import dev.edvinmichovic.userservice.model.AppUser;
import dev.edvinmichovic.userservice.service.AppUserService;
import dev.edvinmichovic.userservice.service.RoleToAppUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AppUserController {

    private final AppUserService appUserService;
    private final RoleToAppUserService roleToAppUserService;

    @GetMapping
    public ResponseEntity<List<AppUser>> getAllUsers() {
        return new ResponseEntity<>(appUserService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<AppUser> addUser(@RequestBody AppUser appUser) {
        return new ResponseEntity<>(appUserService.saveUser(appUser), HttpStatus.CREATED);
    }

    @PostMapping("/assignRole")
    public ResponseEntity<?> assignRole(@RequestBody ObjectNode objectNode) {
        String username = objectNode.get("username").asText();
        String roleName = objectNode.get("roleName").asText();
        roleToAppUserService.addRoleToUser(username, roleName);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
