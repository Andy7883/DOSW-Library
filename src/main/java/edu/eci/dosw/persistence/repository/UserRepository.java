package edu.eci.dosw.persistence.repository;

import edu.eci.dosw.persistence.document.UserDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<UserDocument, String> {

    Optional<UserDocument> findByUsername(String username);
}