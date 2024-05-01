package co.ximple.backendlibrary.infra.resource.dto.response;

import co.ximple.backendlibrary.domain.entity.User;

public record UserResponse(Long id, String name, String email, String phone) {
    public static UserResponse of(User user) {
        return new UserResponse(user.id(), user.name(), user.email(), user.phone());
    }
}
