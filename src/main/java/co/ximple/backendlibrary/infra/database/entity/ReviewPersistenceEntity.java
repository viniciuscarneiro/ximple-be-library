package co.ximple.backendlibrary.infra.database.entity;

import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.Review;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import static java.time.ZoneOffset.UTC;

@Data
@Entity
@Table(name = "review")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReviewPersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    @Column(nullable = false)
    private Double score;
    @ManyToOne(optional = false)
    private ReservationPersistenceEntity reservation;
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    public static ReviewPersistenceEntity of(Review review) {
        var reservation = ReservationPersistenceEntity.of(review.reservation().id());
        return ReviewPersistenceEntity
            .builder()
            .description(review.description())
            .score(review.score())
            .reservation(reservation)
            .createdAt(ZonedDateTime.now(UTC))
            .build();
    }

    public Review toDomainEntity(Reservation reservation) {
        return new Review(reservation.id(), description, score, reservation, createdAt);
    }
}
