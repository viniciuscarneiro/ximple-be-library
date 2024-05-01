package co.ximple.backendlibrary.infra.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ximple.backendlibrary.domain.usecase.BookReservationUseCase;
import co.ximple.backendlibrary.infra.resource.dto.request.CreateBookReservationRequest;
import co.ximple.backendlibrary.infra.resource.dto.response.ReservationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reservation")
public class ReservationResource {

    private final BookReservationUseCase bookReservationUseCase;

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@Valid @RequestBody
                                                      CreateBookReservationRequest createBookReservationRequest) {
        return ResponseEntity.ok(
            ReservationResponse.of(
                bookReservationUseCase.execute(createBookReservationRequest.toDomainEntity())));
    }
}
