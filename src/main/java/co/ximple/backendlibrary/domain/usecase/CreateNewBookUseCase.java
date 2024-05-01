package co.ximple.backendlibrary.domain.usecase;

import co.ximple.backendlibrary.domain.entity.Book;

public interface CreateNewBookUseCase {
    Book execute(Book bok);
}
