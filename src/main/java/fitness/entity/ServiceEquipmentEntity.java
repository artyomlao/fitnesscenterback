package fitness.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity @Table(name = "service_equipment")
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ServiceEquipmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private ServiceEntity service;

    @ManyToOne
    @JoinColumn(name = "equipment_id", referencedColumnName = "id")
    private EquipmentEntity equipment;
}
