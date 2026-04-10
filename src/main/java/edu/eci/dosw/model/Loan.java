package edu.eci.dosw.model;

import java.time.LocalDate;

public class Loan {

    private Book book;
    private User user;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private LoanStatus status;

    public Loan() {}

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

    public Book getBook() { return book; }
    public User getUser() { return user; }
    public LoanStatus getStatus() { return status; }

    public void setBook(Book book) { this.book = book; }
    public void setUser(User user) { this.user = user; }
    public void setLoanDate(LocalDate loanDate) { this.loanDate = loanDate; }
    public void setReturnDate(LocalDate returnDate) { this.returnDate = returnDate; }
    public void setStatus(LoanStatus status) { this.status = status; }
}