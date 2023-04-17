package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    @OneToMany(mappedBy = "orderEntity")
    private List<ServiceEntity> serviceEntities;

    @Basic
    @Column
    private LocalDateTime insertTime;
}
