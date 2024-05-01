package co.ximple.backendlibrary.infra.database.entity;

import co.ximple.backendlibrary.domain.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(name = "email_unique_constraint", columnNames = {"email"}))
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserPersistenceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;

    public static UserPersistenceEntity of(User user) {
        return UserPersistenceEntity
            .builder()
            .name(user.name())
            .email(user.email())
            .phone(user.phone())
            .build();
    }

    public User toDomainEntity() {
        return new User(this.id, this.name, this.email, this.phone);
    }
}
