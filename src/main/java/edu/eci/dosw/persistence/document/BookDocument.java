package edu.eci.dosw.persistence.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "books")
public class BookDocument {

    @Id
    private String id;
    private String title;
    private String author;
    private int quantity;

    public BookDocument() {}

    public BookDocument(String id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getQuantity() { return quantity; }

    public void decreaseQuantity() {
        this.quantity--;
    }
}