package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.RoleRepository;
import es.uma.taw.arkhammovies.dto.RoleDTO;
import es.uma.taw.arkhammovies.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// ================================================================================
// Enrique Ibáñez: 100%
// ================================================================================

@Service
public class RoleService extends DTOService<RoleDTO, Role> {

    @Autowired
    protected RoleRepository roleRepository;

    public RoleDTO findRoleById(Integer id) {
        Role role = this.roleRepository.findById(id).get();

        return this.toDTO(role);
    }

}
