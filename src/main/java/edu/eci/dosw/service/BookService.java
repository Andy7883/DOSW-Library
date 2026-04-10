package edu.eci.dosw.service;

import edu.eci.dosw.persistence.document.BookDocument;
import edu.eci.dosw.persistence.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void addBook(String id, String title, String author, int quantity) {
        bookRepository.save(new BookDocument(id, title, author, quantity));
    }

    public Collection<BookDocument> getAllBooks() {
        return bookRepository.findAll();
    }

    public BookDocument getBookById(String bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public boolean isAvailable(String bookId) {
        BookDocument book = getBookById(bookId);
        return book != null && book.getQuantity() > 0;
    }

    public void decreaseStock(String bookId) {
        BookDocument book = getBookById(bookId);
        if (book != null) {
            book.decreaseQuantity();
            bookRepository.save(book);
        }
    }
}