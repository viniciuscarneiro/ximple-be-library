package co.ximple.backendlibrary.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookReviewAlreadyExistsException extends RuntimeException {
    public BookReviewAlreadyExistsException() {
        super("Book review already exists");
    }
}
