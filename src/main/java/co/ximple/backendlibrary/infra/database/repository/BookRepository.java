package co.ximple.backendlibrary.infra.database.repository;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;

import co.ximple.backendlibrary.infra.database.entity.BookPersistenceEntity;
import java.util.List;

public interface BookRepository extends CrudRepository<BookPersistenceEntity, Long> {
    List<BookPersistenceEntity> findAll(Specification<BookPersistenceEntity> spec);
}
