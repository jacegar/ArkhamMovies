package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "name_UNIQUE", columnNames = {"name"}),
        @UniqueConstraint(name = "permission_UNIQUE", columnNames = {"permission"})
})
public class Role {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "permission", nullable = false)
    private Integer permission;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

}