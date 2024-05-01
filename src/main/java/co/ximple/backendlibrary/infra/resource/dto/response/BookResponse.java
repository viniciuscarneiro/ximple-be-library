package co.ximple.backendlibrary.infra.resource.dto.response;

import co.ximple.backendlibrary.domain.entity.Book;

public record BookResponse(Long id, String isbn, String name, Boolean available, String author) {

    public static BookResponse of(Book book) {
        return new BookResponse(book.id(), book.isbn(), book.title(), book.available(),
            book.author());
    }
}
