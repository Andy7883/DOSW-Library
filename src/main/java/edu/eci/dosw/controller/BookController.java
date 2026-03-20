package edu.eci.dosw.controller;

import edu.eci.dosw.dto.BookDTO;
import edu.eci.dosw.model.Book;
import edu.eci.dosw.service.BookService;
import edu.eci.dosw.util.ValidationUtil;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public void addBook(@RequestBody BookDTO dto) {
        ValidationUtil.notBlank(dto.id, "Book id is required");
        ValidationUtil.notBlank(dto.title, "Title is required");
        ValidationUtil.notBlank(dto.author, "Author is required");
        ValidationUtil.positive(dto.quantity, "Quantity must be positive");

        Book book = new Book(dto.id, dto.title, dto.author);
        bookService.addBook(book, dto.quantity);
    }

    @GetMapping
    public Collection<Book> getAllBooks() {
        return bookService.getAllBooks();
    }
}