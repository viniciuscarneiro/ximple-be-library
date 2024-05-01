package co.ximple.backendlibrary.infra.gateway;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.User;
import co.ximple.backendlibrary.domain.exception.EntityNotFoundException;
import co.ximple.backendlibrary.domain.gateway.ReservationGateway;
import co.ximple.backendlibrary.infra.database.entity.ReservationPersistenceEntity;
import co.ximple.backendlibrary.infra.database.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReservationGatewayImpl implements ReservationGateway {
    private final ReservationRepository reservationRepository;

    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    public Reservation create(Reservation reservation, Book book, User user) {
        return reservationRepository.save(ReservationPersistenceEntity.of(reservation, book, user))
            .toDomainEntity(book, user);
    }

    @Cacheable(cacheNames = "reservation", key = "#id")
    @Override
    public Reservation findById(Long id) {
        var found = reservationRepository.findById(id);
        return found
            .map(reservation ->
                reservation.toDomainEntity(
                    reservation.getBook().toDomainEntity(false),
                    reservation.getUser().toDomainEntity())
            )
            .orElseThrow(() -> new EntityNotFoundException("reservation", id));
    }
}
