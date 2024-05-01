package co.ximple.backendlibrary.domain.entity;

import java.io.Serializable;

public record Book(Long id, String isbn, String title, Boolean available, String author) implements
    Serializable {
    public static Book of(Long bookId) {
        return new Book(bookId, null, null, null, null);
    }

    public static Book of(String isbn, String title, String author) {
        return new Book(null, isbn, title, null, author);
    }
}
