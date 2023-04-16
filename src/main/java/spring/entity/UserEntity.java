package spring.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import spring.model.Role;
import spring.model.Status;

@Entity @Table
@Getter @Setter
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Status status;

    @Enumerated(value = EnumType.STRING)
    @Column
    private Role role;
}
