package co.ximple.backendlibrary.infra.database.repository;

import org.springframework.data.repository.CrudRepository;

import co.ximple.backendlibrary.infra.database.entity.ReservationPersistenceEntity;
import java.time.ZonedDateTime;

public interface ReservationRepository extends CrudRepository<ReservationPersistenceEntity, Long> {
    Boolean existsByBookIdAndReturnDateAfter(Long bookId, ZonedDateTime returnDate);
}
