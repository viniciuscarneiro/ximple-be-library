package co.ximple.backendlibrary.domain.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

public record Reservation(Long id,
                          User user,
                          Book book,
                          ZonedDateTime createdAt,
                          ZonedDateTime returnDate) implements Serializable {
    public static Reservation of(Long reservationId) {
        return new Reservation(reservationId, null, null, null, null);
    }
}
