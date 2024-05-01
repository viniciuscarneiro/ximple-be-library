package co.ximple.backendlibrary.infra.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.ximple.backendlibrary.domain.entity.BookReviewFilterCriteria;
import co.ximple.backendlibrary.domain.usecase.BookReviewUseCase;
import co.ximple.backendlibrary.domain.usecase.FetchBookReviewsUseCase;
import co.ximple.backendlibrary.infra.resource.dto.request.CreateBookReviewRequest;
import co.ximple.backendlibrary.infra.resource.dto.response.Response;
import co.ximple.backendlibrary.infra.resource.dto.response.ReviewResponse;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/reviews")
public class ReviewBookResource {

    private final BookReviewUseCase bookReviewUseCase;
    private final FetchBookReviewsUseCase fetchBookReviewsUseCase;

    @PostMapping
    public ResponseEntity<ReviewResponse> create(@Valid @RequestBody
                                                 CreateBookReviewRequest createBookReviewRequest) {
        return ResponseEntity.ok(
            ReviewResponse.of(
                bookReviewUseCase.execute(createBookReviewRequest.toDomainEntity())));
    }

    @GetMapping("/book/{book-id}")
    public Response<List<ReviewResponse>> getAllReviewsByBook(
        @PathVariable("book-id") Long bookId) {
        return Response.ok(
            fetchBookReviewsUseCase.execute(BookReviewFilterCriteria.of(bookId)).stream()
                .map(ReviewResponse::of).toList());
    }
}
