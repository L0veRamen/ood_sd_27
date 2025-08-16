package ca.bytetube._07_bookreservation;

public class Book extends Resource {
    private String isbn;
    private String author;

    public Book(String id, String name, int quantity, String isbn, String author) {
        super(id, quantity, name);
        this.isbn = isbn;
        this.author = author;
    }
}
