package co.ximple.backendlibrary.infra.resource.dto.request;

import co.ximple.backendlibrary.domain.entity.BookFilterCriteria;

public record GetAllBooksFilterRequest(
    String author,
    String title,
    String isbn,
    Boolean available
) {
    public BookFilterCriteria toDomainEntity() {
        return new BookFilterCriteria(null, isbn, title, available, author);
    }
}
