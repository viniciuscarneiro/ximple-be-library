package co.ximple.backendlibrary.domain.usecase;

import org.springframework.stereotype.Service;

import co.ximple.backendlibrary.domain.entity.BookReviewFilterCriteria;
import co.ximple.backendlibrary.domain.entity.Review;
import co.ximple.backendlibrary.domain.gateway.ReviewGateway;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FetchBookReviewsUseCaseImpl implements FetchBookReviewsUseCase {

    private final ReviewGateway reviewGateway;

    @Override
    public List<Review> execute(BookReviewFilterCriteria bookReviewFilterCriteria) {
        return reviewGateway.findAllByCriteria(bookReviewFilterCriteria);
    }
}
