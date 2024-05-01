package co.ximple.backendlibrary.infra.resource.dto.request;

import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.Review;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record CreateBookReviewRequest(
    String description,
    @NotNull Long reservationId,
    @NotNull @Max(5) @Min(0) Double score
) {
    public Review toDomainEntity() {
        var reservation = Reservation.of(reservationId);
        return new Review(null, description, score, reservation, null);
    }
}
