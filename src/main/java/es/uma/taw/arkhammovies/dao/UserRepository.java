package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.email = :email and u.password = :password")
    public User findUserByEmailAndPassword(@Param("email") String email,
                                           @Param("password") String password);

    @Query("select u from User u where u.email = :email")
    public User findUserByEmail(@Param("email")String email);

    void deleteByEmail(String email);
}
