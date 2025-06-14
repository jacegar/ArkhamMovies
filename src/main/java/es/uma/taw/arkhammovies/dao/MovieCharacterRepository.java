package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.MovieCharacter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieCharacterRepository extends JpaRepository<MovieCharacter, Integer> {
}
