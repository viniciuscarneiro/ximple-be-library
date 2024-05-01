package co.ximple.backendlibrary.domain.usecase;

import org.springframework.stereotype.Service;

import co.ximple.backendlibrary.domain.entity.User;
import co.ximple.backendlibrary.domain.gateway.UserGateway;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CreateNewUserUseCaseImpl implements CreateNewUserUseCase {

    private final UserGateway userGateway;

    @Override
    public User execute(User user) {
        return userGateway.createUser(user);
    }

}
