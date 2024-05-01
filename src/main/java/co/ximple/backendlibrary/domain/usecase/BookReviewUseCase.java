package co.ximple.backendlibrary.domain.usecase;

import co.ximple.backendlibrary.domain.entity.Review;

public interface BookReviewUseCase {
    Review execute(Review review);
}
