package co.ximple.backendlibrary.infra.database.entity;

import co.ximple.backendlibrary.domain.entity.Book;
import co.ximple.backendlibrary.domain.entity.Reservation;
import co.ximple.backendlibrary.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "reservation")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationPersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    private UserPersistenceEntity user;
    @ManyToOne(optional = false)
    private BookPersistenceEntity book;
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;
    @Column(name = "return_date", nullable = false)
    private ZonedDateTime returnDate;

    public static ReservationPersistenceEntity of(Reservation reservation, Book book, User user) {
        return ReservationPersistenceEntity
            .builder()
            .book(BookPersistenceEntity.builder().id(book.id()).build())
            .user(UserPersistenceEntity.builder().id(user.id()).build())
            .id(reservation.id())
            .createdAt(reservation.createdAt())
            .returnDate(reservation.returnDate())
            .build();
    }

    public static ReservationPersistenceEntity of(Long id) {
        return ReservationPersistenceEntity.builder().id(id).build();
    }

    public Reservation toDomainEntity(Book book, User user) {
        return new Reservation(this.id, user, book, this.createdAt, this.returnDate);
    }
}
