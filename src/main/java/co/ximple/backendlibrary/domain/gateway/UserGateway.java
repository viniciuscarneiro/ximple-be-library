package co.ximple.backendlibrary.domain.gateway;

import co.ximple.backendlibrary.domain.entity.User;

public interface UserGateway {

    User createUser(User user);

    User findById(Long userId);
}
