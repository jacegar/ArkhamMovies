package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

// ================================================================================
// José Manuel Fernández: 80%
// Juan Acevedo: 10%
// Eduardo Ariza: 10%
// ================================================================================

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select r from Review r where r.movie.id = :movieId")
    public List<Review> getReviewsByMovieId(Integer movieId);

    @Query("select r from Review r where r.movie.id = :movieId and r.user.id = :userId")
    public Review getReviewById(Integer movieId, Integer userId);

    @Query("select r from Review r where r.user.id = :userId")
    public List<Review> getReviewsByUserId(Integer userId);

    @Query("select avg(r.score) from Review r")
    Double getScoreMean();
}
