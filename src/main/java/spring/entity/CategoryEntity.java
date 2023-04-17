package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
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

    @OneToMany(mappedBy = "categoryEntity")
    private List<ServiceEntity> serviceEntities;
}
