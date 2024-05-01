package co.ximple.backendlibrary.domain.usecase;

import org.springframework.stereotype.Service;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.BookFilterCriteria;
import co.ximple.backendlibrary.domain.gateway.BookGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FetchBooksUseCaseImpl implements FetchBooksUseCase {

    private final BookGateway bookGateway;

    @Override
    public List<Book> execute(BookFilterCriteria bookFilterCriteria) {
        return bookGateway.findAllByCriteria(bookFilterCriteria);
    }
}
