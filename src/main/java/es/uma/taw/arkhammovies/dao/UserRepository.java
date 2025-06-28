package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.nickname = :nickname and u.password = :password")
    public User findUserByNicknameAndPassword(@Param("nickname") String nickname,
                                              @Param("password") String password);

    @Query("select u from User u where u.email = :email")
    public User findUserByEmail(@Param("email")String email);

    @Query("select u from User u where u.nickname = :nickname")
    public User findUserByNickname(@Param("nickname")String nickname);

    void deleteByEmail(String email);

    @Query("select u from User u where u.nickname ilike %:search%")
    public List<User> findUsersBySearch(@Param("search") String search);

    @Query("select count(u) from User u where u.role.id = 2")
    Integer getUserCount();

    @Query("select count(u) from User u where u.role.id = 1")
    Integer getEditorCount();

    @Query("select count(u) from User u where u.role.id = 0")
    Integer getAdminCount();
}
