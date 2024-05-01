package co.ximple.backendlibrary.infra.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ximple.backendlibrary.domain.usecase.CreateNewBookUseCase;
import co.ximple.backendlibrary.domain.usecase.FetchBooksUseCase;
import co.ximple.backendlibrary.infra.resource.dto.request.CreateNewBookRequest;
import co.ximple.backendlibrary.infra.resource.dto.request.GetAllBooksFilterRequest;
import co.ximple.backendlibrary.infra.resource.dto.response.BookResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/books")
public class BookResource {

    private final FetchBooksUseCase fetchBooksUseCase;
    private final CreateNewBookUseCase createNewBookUseCase;

    @PostMapping
    public ResponseEntity<BookResponse> create(
        @Valid @RequestBody CreateNewBookRequest createNewBookRequest) {
        return ResponseEntity.ok(
            BookResponse.of(createNewBookUseCase.execute(createNewBookRequest.toDomainEntity())));
    }

    @GetMapping
    public ResponseEntity<List<BookResponse>> getAllBooks(
        GetAllBooksFilterRequest filterRequest) {
        return ResponseEntity.ok(
            fetchBooksUseCase.execute(filterRequest.toDomainEntity()).stream().map(BookResponse::of)
                .toList());
    }
}
