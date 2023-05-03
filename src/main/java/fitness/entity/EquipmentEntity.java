package fitness.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity @Table(name = "equipment")
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class EquipmentEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Basic
    @Column
    private String name;

    @Basic
    @Column
    private String description;

    @OneToMany(mappedBy = "equipment")
    @JsonIgnore
    private List<ServiceEquipmentEntity> serviceEntities;
}
