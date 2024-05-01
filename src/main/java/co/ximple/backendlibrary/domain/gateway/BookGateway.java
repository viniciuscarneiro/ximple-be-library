package co.ximple.backendlibrary.domain.gateway;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.BookFilterCriteria;
import java.util.List;

public interface BookGateway {
    List<Book> findAllByCriteria(BookFilterCriteria bookFilterCriteria);

    Book findById(Long bookId);

    void isAvailable(Long bookId);

    Book createBook(Book book);
}
