package fitness.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

@Entity @Table(name = "services")
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Basic
    @Column
    private String name;

    @Basic
    @Column
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_entity_id")
    @JsonManagedReference
    private CategoryEntity categoryEntity;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private List<ServiceEquipmentEntity> equipmentEntity;
}
