package co.ximple.backendlibrary.infra.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ximple.backendlibrary.domain.usecase.CreateNewUserUseCase;
import co.ximple.backendlibrary.infra.resource.dto.request.CreateUserRequest;
import co.ximple.backendlibrary.infra.resource.dto.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
public class UserResource {

    private final CreateNewUserUseCase createNewUserUseCase;

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody
                                               CreateUserRequest createUserRequest) {
        return ResponseEntity.ok(
            UserResponse.of(createNewUserUseCase.execute(createUserRequest.toDomainEntity())));
    }
}
