package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.RoleDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "role", uniqueConstraints = {
        @UniqueConstraint(name = "name_UNIQUE", columnNames = {"name"}),
        @UniqueConstraint(name = "permission_UNIQUE", columnNames = {"permission"})
})
public class Role implements Serializable, DTO<RoleDTO> {
    @Id
    @Column(name = "role_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 25)
    private String name;

    @Column(name = "permission", nullable = false)
    private Integer permission;

    @OneToMany(mappedBy = "role")
    private List<User> users = new ArrayList<>();

    @Override
    public RoleDTO toDTO() {
        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId(this.id);
        roleDTO.setName(this.name);
        roleDTO.setPermission(this.permission);

        List<Integer> userIds = new ArrayList<>();
        this.users.forEach((final User user) -> userIds.add(user.getId()));
        roleDTO.setUsers(userIds);

        return roleDTO;
    }

}