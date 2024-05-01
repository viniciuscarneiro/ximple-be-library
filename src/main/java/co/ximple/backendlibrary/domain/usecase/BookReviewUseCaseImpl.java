package co.ximple.backendlibrary.domain.usecase;

import org.springframework.stereotype.Service;

import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.Review;
import co.ximple.backendlibrary.domain.exception.BookReviewAlreadyExistsException;
import co.ximple.backendlibrary.domain.gateway.BookGateway;
import co.ximple.backendlibrary.domain.gateway.ReservationGateway;
import co.ximple.backendlibrary.domain.gateway.ReviewGateway;
import co.ximple.backendlibrary.domain.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookReviewUseCaseImpl implements BookReviewUseCase {

    private final ReviewGateway reviewGateway;
    private final ReservationGateway reservationGateway;
    private final BookGateway bookGateway;
    private final UserGateway userGateway;


    public Reservation execute(Reservation reservation) {
        var book = bookGateway.findById(reservation.book().id());
        var user = userGateway.findById(reservation.user().id());
        bookGateway.isAvailable(reservation.book().id());
        return reservationGateway.create(reservation, book, user);
    }

    @Override
    public Review execute(Review review) {
        validate(review);
        var foundReservation = reservationGateway.findById(review.reservation().id());
        return reviewGateway.create(review, foundReservation);
    }

    private void validate(Review review) {
        var exists = reviewGateway.existsByReservation(review.reservation().id());
        if (exists) {
            throw new BookReviewAlreadyExistsException();
        }
    }
}
