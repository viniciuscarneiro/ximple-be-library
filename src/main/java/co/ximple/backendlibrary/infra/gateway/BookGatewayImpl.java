package co.ximple.backendlibrary.infra.gateway;

import static java.time.ZoneOffset.UTC;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.BookFilterCriteria;
import co.ximple.backendlibrary.domain.exception.BookNotAvailableException;
import co.ximple.backendlibrary.domain.exception.EntityNotFoundException;
import co.ximple.backendlibrary.domain.gateway.BookGateway;
import co.ximple.backendlibrary.infra.database.entity.BookPersistenceEntity;
import co.ximple.backendlibrary.infra.database.repository.BookRepository;
import co.ximple.backendlibrary.infra.database.repository.ReservationRepository;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookGatewayImpl implements BookGateway {

    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;

    @Cacheable(cacheNames = "books")
    @Override
    public List<Book> findAllByCriteria(BookFilterCriteria bookFilterCriteria) {
        return bookRepository.findAll(buildBookCriteriaSpecification(bookFilterCriteria))
            .stream()
            .map(book -> book.toDomainEntity(true)).toList();
    }

    private Specification<BookPersistenceEntity> buildBookCriteriaSpecification(
        BookFilterCriteria criteria) {
        return (root, query, builder) -> {
            var predicates = new ArrayList<Predicate>();
            if (criteria.id() != null) {
                predicates.add(builder.equal(root.get("id"), criteria.id()));
            }
            if (criteria.isbn() != null) {
                predicates.add(builder.equal(root.get("isbn"), criteria.isbn()));
            }
            if (criteria.title() != null) {
                predicates.add(builder.like(builder.lower(root.get("title")),
                    "%%%s%%".formatted(criteria.title().toLowerCase())));
            }
            if (criteria.author() != null) {
                predicates.add(builder.like(builder.lower(root.get("author")),
                    "%%%s%%".formatted(criteria.author().toLowerCase())));
            }
            if (Boolean.TRUE == criteria.available()) {
                var reservationJoin = root.join("reservations", JoinType.LEFT);
                predicates.add(builder.or(
                    builder.isNull(reservationJoin.get("id")),
                    builder.lessThan(reservationJoin.get("returnDate"), LocalDateTime.now())
                ));
            }
            return builder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Cacheable(cacheNames = "book", key = "#bookId")
    @Override
    public Book findById(Long bookId) {
        var found = bookRepository.findById(bookId);
        return found
            .map(BookPersistenceEntity::toDomainEntity)
            .orElseThrow(() -> new EntityNotFoundException("book", bookId));
    }

    @Override
    public void isAvailable(Long bookId) {
        var exists =
            reservationRepository.existsByBookIdAndReturnDateAfter(bookId, ZonedDateTime.now(UTC));
        if (exists) {
            throw new BookNotAvailableException();
        }
    }

    @CacheEvict(cacheNames = "books", allEntries = true)
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(BookPersistenceEntity.of(book)).toDomainEntity();
    }
}
