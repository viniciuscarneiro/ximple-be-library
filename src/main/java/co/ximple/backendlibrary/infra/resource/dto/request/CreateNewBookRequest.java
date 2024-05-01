package co.ximple.backendlibrary.infra.resource.dto.request;

import co.ximple.backendlibrary.domain.entity.Book;
import jakarta.validation.constraints.NotBlank;

public record CreateNewBookRequest(
    @NotBlank String isbn,
    @NotBlank String title,
    @NotBlank String author
) {
    public Book toDomainEntity() {
        return Book.of(isbn, title, author);
    }
}
