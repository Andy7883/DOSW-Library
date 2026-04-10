package edu.eci.dosw.persistence.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "loans")
public class LoanDocument {

    @Id
    private String id;
    private String userId;
    private String bookId;
    private LocalDate loanDate;
    private LocalDate returnDate;

    public LoanDocument(UserDocument userId, BookDocument bookId) {}

    public LoanDocument(String userId, String bookId) {
        this.userId = userId;
        this.bookId = bookId;
        this.loanDate = LocalDate.now();
    }

    public String getId() { return id; }
    public String getUserId() { return userId; }
    public String getBookId() { return bookId; }
    public LocalDate getLoanDate() { return loanDate; }
    public LocalDate getReturnDate() { return returnDate; }
}