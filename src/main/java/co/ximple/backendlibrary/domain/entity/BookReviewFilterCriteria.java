package co.ximple.backendlibrary.domain.entity;

import java.io.Serializable;

public record BookReviewFilterCriteria(Long bookId) implements Serializable {
    public static BookReviewFilterCriteria of(Long bookId) {
        return new BookReviewFilterCriteria(bookId);
    }
}
