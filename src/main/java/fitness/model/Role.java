package fitness.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Role {

    ADMIN("admin"), USER("user");

    private String role;
}
