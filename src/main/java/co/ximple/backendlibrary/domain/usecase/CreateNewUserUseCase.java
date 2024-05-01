package co.ximple.backendlibrary.domain.usecase;

import co.ximple.backendlibrary.domain.entity.User;

public interface CreateNewUserUseCase {
    User execute(User user);
}
