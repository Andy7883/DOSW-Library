package edu.eci.dosw.controller;

import edu.eci.dosw.dto.BookDTO;
import edu.eci.dosw.persistence.document.BookDocument;
import edu.eci.dosw.service.BookService;
import edu.eci.dosw.util.ValidationUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PreAuthorize("hasRole('LIBRARIAN')")
    @PostMapping
    public void addBook(@RequestBody BookDTO dto) {
        ValidationUtil.notBlank(dto.id, "Book id is required");
        ValidationUtil.notBlank(dto.title, "Title is required");
        ValidationUtil.notBlank(dto.author, "Author is required");
        ValidationUtil.positive(dto.quantity, "Quantity must be positive");

        bookService.addBook(
                dto.id,
                dto.title,
                dto.author,
                dto.quantity
        );
    }

    @PreAuthorize("hasAnyRole('USER','LIBRARIAN')")
    @GetMapping
    public Collection<BookDocument> getAllBooks() {
        return bookService.getAllBooks();
    }
}