package edu.eci.dosw.controller;

import edu.eci.dosw.dto.LoanDTO;
import edu.eci.dosw.model.Book;
import edu.eci.dosw.model.Loan;
import edu.eci.dosw.model.User;
import edu.eci.dosw.service.BookService;
import edu.eci.dosw.service.LoanService;
import edu.eci.dosw.service.UserService;
import edu.eci.dosw.util.ValidationUtil;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    private final LoanService loanService;
    private final BookService bookService;
    private final UserService userService;

    public LoanController(LoanService loanService,
                          BookService bookService,
                          UserService userService) {
        this.loanService = loanService;
        this.bookService = bookService;
        this.userService = userService;
    }

    @PostMapping
    public Loan loanBook(@RequestBody LoanDTO dto) {
        ValidationUtil.notBlank(dto.bookId, "Book id is required");
        ValidationUtil.notBlank(dto.userId, "User id is required");

        Book book = bookService.getBookById(dto.bookId);
        User user = userService.getUserById(dto.userId);

        if (book == null || user == null) {
            throw new IllegalArgumentException("Book or User not found");
        }

        return loanService.loanBook(book, user);
    }

    @GetMapping
    public List<Loan> getLoans() {
        return loanService.getLoans();
    }
}