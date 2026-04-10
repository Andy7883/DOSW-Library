package edu.eci.dosw.service;

import edu.eci.dosw.exception.BookNotAvailableException;
import edu.eci.dosw.persistence.document.BookDocument;
import edu.eci.dosw.persistence.document.LoanDocument;
import edu.eci.dosw.persistence.document.UserDocument;
import edu.eci.dosw.persistence.repository.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {

    private final BookService bookService;
    private final UserService userService;
    private final LoanRepository loanRepository;

    public LoanService(BookService bookService,
                       UserService userService,
                       LoanRepository loanRepository) {
        this.bookService = bookService;
        this.userService = userService;
        this.loanRepository = loanRepository;
    }

    public LoanDocument loanBook(String bookId, String userId) {

        BookDocument book = bookService.getBookById(bookId);
        UserDocument user = userService.getUserById(userId);

        if (book == null || user == null) {
            throw new IllegalArgumentException("Book or User not found");
        }

        if (book.getQuantity() <= 0) {
            throw new BookNotAvailableException("Book not available");
        }

        bookService.decreaseStock(bookId);

        return loanRepository.save(
                new LoanDocument(userId, bookId)
        );
    }

    public List<LoanDocument> getLoans() {
        return loanRepository.findAll();
    }
}