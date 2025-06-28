package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

//Autor: Juan Acevedo García 50%

public interface MovieRepository extends JpaRepository<Movie, Integer> {
    //Devuelve las películas más populares, usado en la pantalla de inicio
    @Query("select m from Movie m where m.title ilike %:title% order by m.popularity desc")
    public List<Movie> getMoviesSortedByPopularity(String title);

    //Devuelve las películas más populares, usado en la pantalla de inicio
    @Query("select m from Movie m order by m.popularity desc")
    List<Movie> getAllMoviesSortedByPopularity();

    //Devuelve las películas más recientes, usado en la pantalla de inicio
    @Query("select m from Movie m where m.title ilike %:title% order by m.releaseDate desc")
    public List<Movie> getMoviesSortedByReleaseDate(String title);

    //Devuelve las películas más recientes, usado en la pantalla de inicio
    @Query("select m from Movie m order by m.releaseDate desc")
    public List<Movie> getAllMoviesSortedByReleaseDate();

    //Devuelve las películas que contengan el parámetro title en su título
    @Query("select m from Movie m where m.title ilike %:title%")
    public List<Movie> getMoviesByTitle(String title);

    //Devuelve peliculas que pertenezcan a alguno de los generos de la lista, y que no esten en la lista de gustados por el usuario
    @Query("select distinct m from Movie m join m.genres g where g.id in :genresIds and m not in " +
            "(select m from Movie m join m.usersLiked u on u.id = :userId) and m.title ilike %:title%")
    public List<Movie> getRecommendedMoviesByUserAndGenres(@Param("userId") Integer userId, @Param("genresIds") List<Integer> genresIds, String title);

    //Devuelve peliculas que pertenezcan a alguno de los generos de la lista, y que no esten en la lista de gustados por el usuario sin título
    @Query("select distinct m from Movie m join m.genres g where g.id in :genresIds and m not in " +
            "(select m from Movie m join m.usersLiked u on u.id = :userId)")
    List<Movie> getAllRecommendedMoviesByUserAndGenres(@Param("userId") Integer userId, @Param("genresIds") List<Integer> genresIds);

    //Devuelve las peliculas que le gustan a un usuario
    @Query("select m from Movie m join m.usersLiked u where u.id = :userId and m member of u.moviesLiked")
    public List<Movie> getLikedMoviesByUser(@Param("userId") Integer userId);
    
    @Query("SELECT m FROM Movie m LEFT JOIN m.reviews r where m.title ilike %:title% GROUP BY m.id ORDER BY AVG(r.score) DESC")
    List<Movie> getMoviesSortedByAverageScore(String title);

    @Query("SELECT m FROM Movie m LEFT JOIN m.reviews r GROUP BY m.id ORDER BY AVG(r.score) DESC")
    List<Movie> getAllMoviesSortedByAverageScore();

    //Devuelve las peliculas en las que una persona ha trabajado como crewmember
    @Query("SELECT m FROM Movie m join m.moviecrews mc where mc.person.id = :personId")
    List<Movie> getMoviesWherePersonIsCrew(@Param("personId")Integer personId);

    // Devuelve las películas que contengan una palabra clave concreta
    @Query("select m from Movie m join m.keywords mk where mk.name = :keyword")
    List<Movie> findMoviesByKeyword(@Param("keyword") String keyword);

    // Devuelve las películas que contengan una palabra clave concreta y por título
    @Query("select m from Movie m join m.keywords mk where mk.name = :keyword and m.title ilike %:title%")
    List<Movie> findMoviesByKeywordAndTitle(@Param("keyword") String keyword, @Param("title") String title);

    @Query("select avg(m.budget) from Movie m")
    Integer getBudgetMean();

    @Query("select avg(m.revenue) from Movie m")
    Integer getRevenueMean();

    @Query("select avg(m.runtime) from Movie m")
    Integer getRuntimeMean();

    @Query("select avg(size(m.usersLiked)) from Movie m")
    Double getLikesMean();

    @Query("select m from Movie m order by m.budget desc")
    List<Movie> getAllMoviesSortedByBudget();

    @Query("select m from Movie m order by m.revenue desc")
    List<Movie> getAllMoviesSortedByRevenue();

    @Query("select m from Movie m order by (m.revenue - m.budget) desc")
    List<Movie> getAllMoviesSortedByProfit();

    @Query("select m from Movie m order by m.runtime desc")
    List<Movie> getAllMoviesSortedByDuration();

    @Query("SELECT m.title, AVG(r.score) FROM Movie m LEFT JOIN m.reviews r GROUP BY m.id ORDER BY AVG(r.score) DESC")
    List<Object[]> getSortedMovieScores();

    @Query("select m.title, size(m.usersLiked) from Movie m order by size(m.usersLiked) desc")
    List<Object[]> getSortedFavouritedMovies();

}
