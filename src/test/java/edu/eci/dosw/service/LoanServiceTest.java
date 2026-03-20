package edu.eci.dosw.service;

import edu.eci.dosw.exception.BookNotAvailableException;
import edu.eci.dosw.model.Book;
import edu.eci.dosw.model.Loan;
import edu.eci.dosw.model.LoanStatus;
import edu.eci.dosw.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoanServiceTest {

    private BookService bookService;
    private UserService userService;
    private LoanService loanService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
        userService = new UserService();
        loanService = new LoanService(bookService);
    }

    @Test
    void shouldLoanBookSuccessfully() {
        Book book = new Book("B1", "Clean Architecture", "Robert Martin");
        User user = new User("U1", "Pedro");

        bookService.addBook(book, 1);
        userService.addUser(user);

        Loan loan = loanService.loanBook(book, user);

        assertNotNull(loan);
        assertEquals(book, loan.getBook());
        assertEquals(user, loan.getUser());
        assertEquals(LoanStatus.ACTIVE, loan.getStatus());
        assertFalse(bookService.isAvailable("B1"));
    }

    @Test
    void shouldThrowExceptionWhenBookIsNotAvailable() {
        Book book = new Book("B2", "Domain-Driven Design", "Eric Evans");
        User user = new User("U2", "Ana");

        bookService.addBook(book, 0);
        userService.addUser(user);

        assertThrows(BookNotAvailableException.class, () -> {
            loanService.loanBook(book, user);
        });
    }
}