package edu.eci.dosw.controller;

import edu.eci.dosw.dto.LoginDTO;
import edu.eci.dosw.persistence.document.UserDocument;
import edu.eci.dosw.persistence.repository.UserRepository;
import edu.eci.dosw.security.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final JwtService jwtService;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.jwtService = new JwtService();
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) {

        UserDocument user = userRepository
                .findByUsername(loginDTO.username)
                .orElse(null);

        if (user == null || !user.getPassword().equals(loginDTO.password)) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        String token = jwtService.generateToken(
                user.getUsername(),
                user.getRole()
        );

        return ResponseEntity.ok(token);
    }
}