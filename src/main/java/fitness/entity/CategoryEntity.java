package fitness.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity @Table(name = "categories")
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Basic
    @Column
    private String name;

    @Basic
    @Column
    private String description;

    @OneToMany(mappedBy = "categoryEntity", cascade = CascadeType.ALL)
    @JsonBackReference(value = "categoryService")
    @JsonIgnore
    private List<ServiceEntity> serviceEntities;
}
