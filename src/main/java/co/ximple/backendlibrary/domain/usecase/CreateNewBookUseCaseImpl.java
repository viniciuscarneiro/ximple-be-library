package co.ximple.backendlibrary.domain.usecase;

import org.springframework.stereotype.Service;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.gateway.BookGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateNewBookUseCaseImpl implements CreateNewBookUseCase {

    private final BookGateway bookGateway;

    @Override
    public Book execute(Book book) {
        return bookGateway.createBook(book);
    }
}
