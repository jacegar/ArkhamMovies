package es.uma.taw.arkhammovies.dao;


import es.uma.taw.arkhammovies.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Autor: Juan Acevedo García 100%

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("select g from Genre g join g.moviesgenre m join m.usersLiked u " +
            "where u.id = :userId " +
            "group by g.id " +
            "order by count(m) desc")
    public List<Genre> getLikedGenresOrderedByFrequency(@Param("userId") Integer userId);

    @Query("select g from Genre g join g.moviesgenre mg where mg.id = :movieId")
    List<Genre> findGenresByMovie(@Param("movieId") Integer movieId);

    @Query("select g.id, g.name, count(m) from Genre g join g.moviesgenre m " +
            "group by g.id " +
            "order by count(m) desc")
    List<Object[]> getGenresOrderedByFrequency();
}
