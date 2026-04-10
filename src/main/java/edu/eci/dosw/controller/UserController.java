package edu.eci.dosw.controller;

import edu.eci.dosw.dto.UserDTO;
import edu.eci.dosw.model.User;
import edu.eci.dosw.model.Role;
import edu.eci.dosw.persistence.document.UserDocument;
import edu.eci.dosw.service.UserService;
import edu.eci.dosw.util.ValidationUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public void addUser(@RequestBody UserDTO dto) {
        ValidationUtil.notBlank(dto.id, "User id is required");
        ValidationUtil.notBlank(dto.name, "User name is required");

        userService.addUser(
                new User(
                        dto.id,
                        dto.name,
                        dto.username,
                        dto.password,
                        Role.USER
                )
        );
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @GetMapping
    public Collection<UserDocument> getAllUsers() {
        return userService.getAllUsers();
    }
}