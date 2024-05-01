package co.ximple.backendlibrary.infra.gateway;

import org.springframework.stereotype.Component;

import co.ximple.backendlibrary.domain.entity.User;
import co.ximple.backendlibrary.domain.exception.EntityNotFoundException;
import co.ximple.backendlibrary.domain.gateway.UserGateway;
import co.ximple.backendlibrary.infra.database.entity.UserPersistenceEntity;
import co.ximple.backendlibrary.infra.database.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class UserGatewayImpl implements UserGateway {
    private final UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(UserPersistenceEntity.of(user)).toDomainEntity();
    }

    @Override
    public User findById(Long userId) {
        var found = userRepository.findById(userId);
        return found.map(UserPersistenceEntity::toDomainEntity)
            .orElseThrow(() -> new EntityNotFoundException("user", userId));
    }
}
