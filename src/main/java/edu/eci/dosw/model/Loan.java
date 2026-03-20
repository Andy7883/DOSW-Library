package edu.eci.dosw.model;

import java.time.LocalDate;

public class Loan {

    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan(Book book, User user) {
        this.book = book;
        this.user = user;
        this.loanDate = LocalDate.now();
        this.status = LoanStatus.ACTIVE;
    }

    public void returnBook() {
        this.status = LoanStatus.RETURNED;
        this.returnDate = LocalDate.now();
    }

    public LoanStatus getStatus() {
        return status;
    }

    public Book getBook() {
        return book;
    }

    public User getUser() {
        return user;
    }
}