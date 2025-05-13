package es.uma.taw.arkhammovies.dto;

import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.ReviewId;
import es.uma.taw.arkhammovies.entity.User;
import lombok.Data;

@Data
public class ReviewDTO {
    private ReviewId id;
    private Movie movie_id;
    private User user_id;
    private Integer score;
    private String text;
}
