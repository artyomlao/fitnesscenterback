package fitness.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ServiceDTO {

    private String name;
    private Double price;
    private Long categoryId;
}
