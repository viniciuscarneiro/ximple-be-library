package co.ximple.backendlibrary.infra.database.repository;

import org.springframework.data.repository.CrudRepository;

import co.ximple.backendlibrary.infra.database.entity.UserPersistenceEntity;

public interface UserRepository extends CrudRepository<UserPersistenceEntity, Long> {
}
