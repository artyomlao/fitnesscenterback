package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
public class ServiceEntity {

    @Id
    @Basic
    @GeneratedValue
    private Long id;

    @Basic
    @Column
    private String name;

    @Basic
    @Column
    private Double price;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "category_entity_id")
    private CategoryEntity categoryEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_entity_id", nullable = false)
    private OrderEntity orderEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "equipment_entity_id")
    private EquipmentEntity equipmentEntity;
}
