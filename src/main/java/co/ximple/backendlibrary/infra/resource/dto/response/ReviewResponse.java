package co.ximple.backendlibrary.infra.resource.dto.response;

import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.Review;
import java.time.ZonedDateTime;

public record ReviewResponse(Long id,
                             String description,
                             Reservation reservation,
                             Double score,
                             ZonedDateTime createdAt) {
    public static ReviewResponse of(Review review) {
        return new ReviewResponse(
            review.id(),
            review.description(),
            review.reservation(),
            review.score(),
            review.createdAt());
    }
}
