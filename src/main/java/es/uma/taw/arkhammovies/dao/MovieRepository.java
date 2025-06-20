package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Autor: Juan Acevedo García 50%

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //Devuelve las películas más populares, usado en la pantalla de inicio
    @Query("select m from Movie m where m.title ilike %:title% order by m.popularity desc")
    public List<Movie> getMoviesSortedByPopularity(String title);


    //Devuelve las películas más recientes, usado en la pantalla de inicio
    @Query("select m from Movie m where m.title ilike %:title% order by m.releaseDate desc")
    public List<Movie> getMoviesSortedByReleaseDate(String title);

    //Devuelve las películas que contengan el parámetro title en su título
    @Query("select m from Movie m where m.title ilike %:title%")
    public List<Movie> getMoviesByTitle(String title);

    //Devuelve peliculas que pertenezcan a alguno de los generos de la lista, y que no esten en la lista de gustados por el usuario
    @Query("select distinct m from Movie m join m.genres g where g.id in :genresIds and m not in " +
            "(select m from Movie m join m.usersLiked u on u.id = :userId) and m.title ilike %:title%")
    public List<Movie> getRecommendedMoviesByUserAndGenres(@Param("userId") Integer userId, @Param("genresIds") List<Integer> genresIds, String title);

    //Devuelve las peliculas que le gustan a un usuario
    @Query("select m from Movie m join m.usersLiked u where u.id = :userId and m member of u.moviesLiked")
    public List<Movie> getLikedMoviesByUser(@Param("userId") Integer userId);
    
    @Query("SELECT m FROM Movie m LEFT JOIN m.reviews r where m.title ilike %:title% GROUP BY m.id ORDER BY AVG(r.score) DESC")
    List<Movie> getMoviesSortedByAverageScore(String title);
}
