package co.ximple.backendlibrary.infra.resource.dto.response;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.User;
import java.time.ZonedDateTime;

public record ReservationResponse(Long id,
                                  User user,
                                  Book book,
                                  ZonedDateTime createdAt,
                                  ZonedDateTime returnDate) {
    public static ReservationResponse of(Reservation reservation) {
        return new ReservationResponse(reservation.id(),
            reservation.user(),
            reservation.book(),
            reservation.createdAt(),
            reservation.returnDate()
        );
    }
}
