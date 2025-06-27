package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Moviekeyword;
import es.uma.taw.arkhammovies.entity.MoviekeywordId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MoviekeywordRepository extends JpaRepository<Moviekeyword, MoviekeywordId> {

    @Modifying
    @Query("delete Moviekeyword mk where mk.movie.id = :movieId")
    void deleteMovieKeywordsByMovieId(@Param("movieId") Integer movieId);

}
