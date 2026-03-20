package edu.eci.dosw.service;

import edu.eci.dosw.model.Book;import edu.eci.dosw.exception.BookNotAvailableException;
import edu.eci.dosw.model.Loan;
import edu.eci.dosw.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanService {

    private final BookService bookService;
    private final List<Loan> loans = new ArrayList<>();

    public LoanService(BookService bookService) {
        this.bookService = bookService;
    }

    public Loan loanBook(Book book, User user) {
        if (!bookService.isAvailable(book.getId())) {
            throw new BookNotAvailableException("Book not available");
        }

        bookService.decreaseStock(book.getId());
        Loan loan = new Loan(book, user);
        loans.add(loan);
        return loan;
    }

    public List<Loan> getLoans() {
        return loans;
    }
}
