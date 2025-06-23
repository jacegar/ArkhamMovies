package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Integer> {

    //Devuelve los personajes que contengan el parámetro name en su nombre
    @Query("select c from MovieCharacter c where c.name ilike %:name%")
    public List<MovieCharacter> getCharactersByName(String name);

    //Devuelve los personajes que aparecen en una pelicula que le gusta al usuario
    @Query("select c from MovieCharacter c join c.movie m join m.usersLiked u where u.id = :userId and c.name ilike %:name%")
    List<MovieCharacter> findLikedCharactersFromUser(Integer userId, String name);
}
