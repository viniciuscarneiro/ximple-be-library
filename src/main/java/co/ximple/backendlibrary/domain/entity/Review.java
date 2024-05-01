package co.ximple.backendlibrary.domain.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public record Review(Long id,
                     String description,
                     Double score,
                     Reservation reservation,
                     ZonedDateTime createdAt) implements Serializable {
}
