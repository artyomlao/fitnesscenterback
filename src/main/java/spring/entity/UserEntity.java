package spring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import spring.model.Role;
import spring.model.Status;

@Entity @Table
@Getter @Setter
@NoArgsConstructor
@Accessors(chain = true)
public class UserEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column
    private String email;

    @Column
    @JsonIgnore
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
