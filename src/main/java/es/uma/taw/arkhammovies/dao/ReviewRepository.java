package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    @Query("select r from Review r where r.movie.id = :movieId")
    public List<Review> getReviewsByMovieId(Integer movieId);

    @Query("select r from Review r where r.movie.id = :movieId and r.user.id = :userId")
    public Review getReviewById(Integer movieId, Integer userId);

}
