package co.ximple.backendlibrary.domain.usecase;

import co.ximple.backendlibrary.domain.entity.Reservation;

public interface BookReservationUseCase {
    Reservation execute(Reservation reservation);
}
