package edu.eci.dosw.service;

import edu.eci.dosw.model.Role;
import edu.eci.dosw.model.User;
import edu.eci.dosw.persistence.document.UserDocument;
import edu.eci.dosw.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;

        userRepository.findByUsername("admin")
                .orElseGet(() ->
                        userRepository.save(
                                new UserDocument(
                                        "ADMIN",
                                        "Administrator",
                                        "admin",
                                        "admin",
                                        Role.LIBRARIAN
                                )
                        )
                );
    }

    public void addUser(User user) {
        userRepository.save(
                new UserDocument(
                        user.getId(),
                        user.getName(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getRole()
                )
        );
    }

    public Collection<UserDocument> getAllUsers() {
        return userRepository.findAll();
    }

    public UserDocument getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }
}