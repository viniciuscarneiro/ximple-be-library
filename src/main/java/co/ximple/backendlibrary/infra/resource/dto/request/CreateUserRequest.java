package co.ximple.backendlibrary.infra.resource.dto.request;

import co.ximple.backendlibrary.domain.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(@NotBlank @Size(max = 200) String name,
                                @Email @Size(max = 70) String email,
                                @NotBlank @Size(max = 20) String phone) {
    public User toDomainEntity() {
        return new User(null, name, email, phone);
    }
}
