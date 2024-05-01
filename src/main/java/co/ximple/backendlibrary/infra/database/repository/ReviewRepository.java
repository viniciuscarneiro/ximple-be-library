package co.ximple.backendlibrary.infra.database.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import co.ximple.backendlibrary.infra.database.entity.ReviewPersistenceEntity;
import java.util.List;

public interface ReviewRepository extends CrudRepository<ReviewPersistenceEntity, Long> {
    Boolean existsByReservationId(Long reservationId);

    List<ReviewPersistenceEntity> findAll(Specification<ReviewPersistenceEntity> spec);
}
