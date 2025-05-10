package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Movie;
import jakarta.persistence.OrderBy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //Devuelve las películas más populares, usado en la pantalla de inicio
    @Query("select m from Movie m order by m.popularity desc")
    public List<Movie> getMoviesSortedByPopularity();


    //Devuelve las películas más recientes, usado en la pantalla de inicio
    @Query("select m from Movie m order by m.releaseDate desc")
    public List<Movie> getMoviesSortedByReleaseDate();

    //Devuelve las películas que contengan el parámetro title en su título
    @Query("select m from Movie m where m.title like %:title%")
    public List<Movie> getMoviesByTitle(String title);
}
