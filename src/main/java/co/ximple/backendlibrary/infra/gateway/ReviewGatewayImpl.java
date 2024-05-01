package co.ximple.backendlibrary.infra.gateway;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import co.ximple.backendlibrary.domain.entity.BookReviewFilterCriteria;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.Review;
import co.ximple.backendlibrary.domain.gateway.ReviewGateway;
import co.ximple.backendlibrary.infra.database.entity.ReviewPersistenceEntity;
import co.ximple.backendlibrary.infra.database.repository.ReviewRepository;
import jakarta.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReviewGatewayImpl implements ReviewGateway {

    private final ReviewRepository reviewRepository;

    @CacheEvict(cacheNames = "book-reviews", key = "#reservation.book().id()")
    @Override
    public Review create(Review review, Reservation reservation) {
        return reviewRepository.save(ReviewPersistenceEntity.of(review))
            .toDomainEntity(reservation);
    }

    @Override
    public Boolean existsByReservation(Long reservationId) {
        return reviewRepository.existsByReservationId(reservationId);
    }

    @Cacheable(cacheNames = "book-reviews", key = "#bookReviewFilterCriteria.bookId()")
    @Override
    public List<Review> findAllByCriteria(BookReviewFilterCriteria bookReviewFilterCriteria) {
        return reviewRepository.findAll(
                buildReviewCriteriaSpecification(bookReviewFilterCriteria))
            .stream()
            .map(book -> book.toDomainEntity(
                    book.getReservation().toDomainEntity(
                        book.getReservation().getBook().toDomainEntity(),
                        book.getReservation().getUser().toDomainEntity()
                    )
                )
            ).toList();
    }

    private Specification<ReviewPersistenceEntity> buildReviewCriteriaSpecification(
        BookReviewFilterCriteria criteria) {
        return (root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (criteria.bookId() != null) {
                predicates.add(builder.equal(root.get("reservation").get("book").get("id"),
                    criteria.bookId()));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
