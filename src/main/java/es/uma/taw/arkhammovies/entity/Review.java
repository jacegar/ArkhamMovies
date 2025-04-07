package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "review", indexes = {
        @Index(name = "user_id_idx", columnList = "user_id")
})
public class Review {
    @EmbeddedId
    private ReviewId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private es.uma.taw.arkhammovies.entity.User user;

    @Column(name = "score", nullable = false)
    private Integer score;

    @Column(name = "text", length = 200)
    private String text;

}