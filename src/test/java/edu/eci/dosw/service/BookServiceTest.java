package edu.eci.dosw.service;

import edu.eci.dosw.model.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookService = new BookService();
    }

    @Test
     void shouldAddBookWithQuantity() {
        Book book = new Book("B1", "Clean Code", "Robert Martin");

        bookService.addBook(book, 5);

        assertEquals(1, bookService.getAllBooks().size());
        assertTrue(bookService.isAvailable("B1"));
    }

    @Test
    void shouldDecreaseAndIncreaseStockCorrectly() {
        Book book = new Book("B2", "Refactoring", "Martin Fowler");
        bookService.addBook(book, 1);

        assertTrue(bookService.isAvailable("B2"));

        bookService.decreaseStock("B2");
        assertFalse(bookService.isAvailable("B2"));

        bookService.increaseStock("B2");
        assertTrue(bookService.isAvailable("B2"));
    }

    @Test
    void shouldReturnNullWhenBookDoesNotExist() {
        Book result = bookService.getBookById("NON_EXISTENT");

        assertNull(result);
    }
}