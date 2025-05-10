package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
