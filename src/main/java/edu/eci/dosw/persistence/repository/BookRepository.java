package edu.eci.dosw.persistence.repository;

import edu.eci.dosw.persistence.document.BookDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookRepository extends MongoRepository<BookDocument, String> {
}