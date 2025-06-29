package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// ================================================================================
// Enrique Ibáñez: 100%
// ================================================================================

public interface RoleRepository extends JpaRepository<Role, Integer> {
}
