package co.ximple.backendlibrary.domain.entity;

import java.io.Serializable;

public record User(Long id, String name, String email, String phone) implements Serializable {
    public static User of(Long userId) {
        return new User(userId, null, null, null);
    }
}
