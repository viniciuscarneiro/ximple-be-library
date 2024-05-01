package co.ximple.backendlibrary.domain.usecase;

import co.ximple.backendlibrary.domain.entity.BookReviewFilterCriteria;
import co.ximple.backendlibrary.domain.entity.Review;
import java.util.List;

public interface FetchBookReviewsUseCase {
    List<Review> execute(BookReviewFilterCriteria bookReviewFilterCriteria);
}
