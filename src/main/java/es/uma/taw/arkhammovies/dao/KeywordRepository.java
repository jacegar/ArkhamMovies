package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

    Keyword findKeywordByName(String name);

    @Query("select k from Keyword k join k.movies km where km.id = :movieId")
    List<Keyword> findKeywordsByMovieId(@Param("movieId") Integer movieId);
}
