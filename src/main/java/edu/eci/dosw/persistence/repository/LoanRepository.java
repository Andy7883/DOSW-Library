package edu.eci.dosw.persistence.repository;

import edu.eci.dosw.persistence.document.LoanDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface LoanRepository extends MongoRepository<LoanDocument, String> {

    List<LoanDocument> findByUserId(String userId);
}