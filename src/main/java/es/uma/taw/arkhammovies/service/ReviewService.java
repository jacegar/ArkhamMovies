package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.ReviewRepository;
import es.uma.taw.arkhammovies.dao.UserRepository;
import es.uma.taw.arkhammovies.dto.ReviewDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.Review;
import es.uma.taw.arkhammovies.entity.ReviewId;
import es.uma.taw.arkhammovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService extends DTOService<ReviewDTO, Review>{

    @Autowired ReviewRepository reviewRepository;
    @Autowired MovieRepository movieRepository;
    @Autowired UserRepository userRepository;

    public List<ReviewDTO> findByMovieId(Integer movieId) {
        List<Review> reviews = this.reviewRepository.getReviewsByMovieId(movieId);
        return this.entity2DTO(reviews);
    }

    public List<ReviewDTO> findByUserId(Integer userId) {
        List<Review> reviews = this.reviewRepository.getReviewsByUserId(userId);
        return this.entity2DTO(reviews);
    }

    public Double findScoreMean() {
        return reviewRepository.getScoreMean();
    }

    public void addReview(Integer movieId, Integer userId, Integer score, String review) {
        Review reviewObj = new Review();

        ReviewId reviewId = new ReviewId();
        reviewId.setUserId(userId);
        reviewId.setMovieId(movieId);

        reviewObj.setId(reviewId);

        User user = this.userRepository.getReferenceById(userId);
        Movie movie = this.movieRepository.getReferenceById(movieId);

        reviewObj.setUser(user);
        reviewObj.setMovie(movie);
        reviewObj.setScore(score);
        reviewObj.setText(review);

        this.reviewRepository.save(reviewObj);
    }

    public void removeById(Integer movieId, Integer userId) {
        Review review = this.reviewRepository.getReviewById(movieId, userId);
        this.reviewRepository.delete(review);
    }

    @Override
    public List<ReviewDTO> entity2DTO(List<Review> reviews) {
        return reviews.stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ReviewDTO toDTO(Review review) {
        ReviewDTO dto = new ReviewDTO();
        dto.setMovie_id(review.getMovie());
        dto.setUser_id(review.getUser());
        dto.setText(review.getText());
        dto.setScore(review.getScore());
        return dto;
    }
}
