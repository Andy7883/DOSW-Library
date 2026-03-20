package edu.eci.dosw.controller;

import edu.eci.dosw.dto.UserDTO;
import edu.eci.dosw.model.User;
import edu.eci.dosw.service.UserService;
import edu.eci.dosw.util.ValidationUtil;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.Collection;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@RequestBody UserDTO dto) {
        ValidationUtil.notBlank(dto.id, "User id is required");
        ValidationUtil.notBlank(dto.name, "User name is required");

        userService.addUser(new User(dto.id, dto.name));
    }

    @GetMapping
    public Collection<User> getAllUsers() {
        return userService.getAllUsers();
    }
}