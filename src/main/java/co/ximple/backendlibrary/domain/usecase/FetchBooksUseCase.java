package co.ximple.backendlibrary.domain.usecase;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.BookFilterCriteria;
import java.util.List;

public interface FetchBooksUseCase {
    List<Book> execute(BookFilterCriteria bookFilterCriteria);
}
