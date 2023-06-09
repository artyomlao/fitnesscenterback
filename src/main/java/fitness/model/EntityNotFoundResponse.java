package fitness.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class EntityNotFoundResponse {

    private String entity;
    private String message;

    public EntityNotFoundResponse(final String message) {
        this.message = message;
    }
}
