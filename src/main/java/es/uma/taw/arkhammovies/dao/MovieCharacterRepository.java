package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Integer> {

    //Devuelve los personajes que contengan el parámetro name en su nombre completo
    @Query("select c from MovieCharacter c where " +
            "concat(c.name, ' ', coalesce(c.surname1, ''), ' ', coalesce(c.surname2, '')) ilike %:name%")
    public List<MovieCharacter> getCharactersByNameOrSurname(String name);

    //Devuelve los personajes que aparecen en una pelicula que le gusta al usuario
    @Query("select c from MovieCharacter c join c.movie m join m.usersLiked u where u.id = :userId and " +
            "(concat(c.name, ' ', coalesce(c.surname1, ''), ' ', coalesce(c.surname2, '')) ilike %:name%)")
    List<MovieCharacter> findLikedCharactersFromUser(Integer userId, String name);
}
