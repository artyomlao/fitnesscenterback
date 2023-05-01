package fitness.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class OrderDTO {

    private Long serviceId;
    private Long userId;
}
