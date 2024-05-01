package co.ximple.backendlibrary.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BookNotAvailableException extends RuntimeException {
    public BookNotAvailableException() {
        super("Given book is not available");
    }
}
