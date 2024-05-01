package co.ximple.backendlibrary.domain.entity;

import java.io.Serializable;

public record BookFilterCriteria(Long id, String isbn, String title, Boolean available,
                                 String author) implements
    Serializable {
}
