package co.ximple.backendlibrary.domain.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EntityNotFoundException extends RuntimeException {
    private final String entityName;
    private final Long entityId;

    public EntityNotFoundException(String entityName, Long entityId) {
        super("Entity %s with id %s not found".formatted(entityName, entityId));
        this.entityId = entityId;
        this.entityName = entityName;
    }
}
