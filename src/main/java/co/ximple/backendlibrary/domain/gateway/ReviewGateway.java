package co.ximple.backendlibrary.domain.gateway;

import co.ximple.backendlibrary.domain.entity.BookReviewFilterCriteria;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.Review;
import java.util.List;

public interface ReviewGateway {
    Review create(Review review, Reservation reservation);

    Boolean existsByReservation(Long reservationId);

    List<Review> findAllByCriteria(
        BookReviewFilterCriteria bookReviewFilterCriteria);
}
