package es.uma.taw.arkhammovies.dao;


import es.uma.taw.arkhammovies.entity.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
    @Query("select g from Genre g join g.moviesgenre m join m.usersLiked u " +
            "where u.id = :userId " +
            "group by g.id " +
            "order by count(m) desc")
    public List<Genre> getLikedGenresOrderedByFrequency(Integer userId);
}
