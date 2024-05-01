package co.ximple.backendlibrary.domain.gateway;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.User;

public interface ReservationGateway {
    Reservation create(Reservation reservation, Book book, User user);

    Reservation findById(Long id);
}
