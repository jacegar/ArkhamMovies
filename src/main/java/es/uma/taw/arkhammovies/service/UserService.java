package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.RoleRepository;
import es.uma.taw.arkhammovies.dao.UserRepository;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService extends DTOService<UserDTO, User> {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RoleRepository roleRepository;

    public UserDTO findUserByNicknameAndPassword(String nickname, String password) {
        User user = this.userRepository.findUserByNicknameAndPassword(nickname, password);

        return user != null ? this.toDTO(user) : null;
    }

    public void registerUser(UserDTO userDTO) {
        User user = new User();
        user.setNickname(userDTO.getNickname());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRole(this.roleRepository.findById(2).get()); // Suponiendo que el id 2 sea rol usuario

        this.userRepository.save(user);
        userDTO.setId(user.getId());
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

        if (user != null && user.getRole().getId()!=0) { // No se pueden borrar admins, se hace desde la BD
            this.userRepository.delete(user);
            eliminado = true;
        }
        return eliminado;
    }

    public UserDTO findUserByNickname(String nickname) {
        User user = this.userRepository.findUserByNickname(nickname);

        return user != null ? this.toDTO(user) : null;
    }

    public List<UserDTO> findUsersBySearch(String search) {
        List<User> users = this.userRepository.findUsersBySearch(search);

        return this.entity2DTO(users);
    }

    public Integer findUserCount() {
        return userRepository.getUserCount();
    }

    public Integer findEditorCount() {
        return userRepository.getEditorCount();
    }

    public Integer findAdminCount() {
        return userRepository.getAdminCount();
    }

    public Map<String, Integer> getSortedUserReviews() {
        List<Object[]> results = userRepository.getSortedUserReviews();
        return MovieService.getCountMap(results);
    }

    public Map<String, Integer> getSortedUserLikes() {
        List<Object[]> results = userRepository.getSortedUserLikes();
        return MovieService.getCountMap(results);
    }

}
