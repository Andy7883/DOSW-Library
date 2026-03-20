package edu.eci.dosw.service;

import edu.eci.dosw.model.Book;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    private final Map<String, Book> books = new HashMap<>();
    private final Map<String, Integer> stock = new HashMap<>();

    public void addBook(Book book, int quantity) {
        books.put(book.getId(), book);
        stock.put(book.getId(), quantity);
    }

    public Collection<Book> getAllBooks() {
        return books.values();
    }

    public Book getBookById(String id) {
        return books.get(id);
    }

    public boolean isAvailable(String bookId) {
        return stock.getOrDefault(bookId, 0) > 0;
    }

    public void decreaseStock(String bookId) {
        stock.put(bookId, stock.get(bookId) - 1);
    }

    public void increaseStock(String bookId) {
        stock.put(bookId, stock.get(bookId) + 1);
    }
}