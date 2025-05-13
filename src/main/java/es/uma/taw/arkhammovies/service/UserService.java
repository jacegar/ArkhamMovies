package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.RoleRepository;
import es.uma.taw.arkhammovies.dao.UserRepository;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends DTOService<UserDTO, User> {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    public UserDTO findUserByEmailAndPassword(String email, String password) {
        User user = this.userRepository.findUserByEmailAndPassword(email, password);

        return user != null ? this.toDTO(user) : null;
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(this.roleRepository.findById(1).get()); // Suponiendo que el id 1 sea rol usuario

        this.userRepository.save(user);
    }

    public UserDTO findUserByEmail(String email) {
        User user = this.userRepository.findUserByEmail(email);

        return user != null ? this.toDTO(user) : null;
    }

    public UserDTO findUserById(Integer id) {
        User user = this.userRepository.findById(id).orElse(null);

        return user != null ? this.toDTO(user) : null;
    }

    public boolean removeUserByEmail(String email) {
        boolean eliminado = false;
        User user = this.userRepository.findUserByEmail(email);

        if (user != null) {
            this.userRepository.delete(user);
            eliminado = true;
        }
        return eliminado;
    }
}
