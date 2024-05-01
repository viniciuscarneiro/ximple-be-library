package co.ximple.backendlibrary.infra.database.entity;

import co.ximple.backendlibrary.domain.entity.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "book", uniqueConstraints = @UniqueConstraint(name = "isbn_unique_constraint", columnNames = {"isbn"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookPersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @OneToMany(mappedBy = "book")
    private List<ReservationPersistenceEntity> reservations;

    public static BookPersistenceEntity of(Book book) {
        return BookPersistenceEntity
            .builder()
            .id(book.id())
            .isbn(book.isbn())
            .title(book.title())
            .author(book.author())
            .build();
    }

    public Book toDomainEntity(Boolean available) {
        return new Book(id, isbn, title, available, author);
    }

    public Book toDomainEntity() {
        return new Book(id, isbn, title, null, author);
    }
}
