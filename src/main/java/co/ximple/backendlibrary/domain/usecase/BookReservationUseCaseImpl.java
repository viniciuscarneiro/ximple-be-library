package co.ximple.backendlibrary.domain.usecase;

import org.springframework.stereotype.Service;

import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.gateway.BookGateway;
import co.ximple.backendlibrary.domain.gateway.ReservationGateway;
import co.ximple.backendlibrary.domain.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookReservationUseCaseImpl implements BookReservationUseCase {

    private final ReservationGateway reservationGateway;
    private final BookGateway bookGateway;
    private final UserGateway userGateway;

    @Override
    public Reservation execute(Reservation reservation) {
        var book = bookGateway.findById(reservation.book().id());
        var user = userGateway.findById(reservation.user().id());
        bookGateway.isAvailable(reservation.book().id());
        return reservationGateway.create(reservation, book, user);
    }
}
