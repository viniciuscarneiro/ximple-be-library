package co.ximple.backendlibrary.infra.resource.dto.request;

import static java.time.ZoneOffset.UTC;
import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.User;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import java.time.ZonedDateTime;

public record CreateBookReservationRequest(
    @NotNull Long userId, @NotNull Long bookId, @NotNull @Future ZonedDateTime returnDate
) {
    public Reservation toDomainEntity() {
        return new Reservation(
            null,
            User.of(userId),
            Book.of(bookId),
            ZonedDateTime.now(UTC),
            returnDate
        );
    }
}
