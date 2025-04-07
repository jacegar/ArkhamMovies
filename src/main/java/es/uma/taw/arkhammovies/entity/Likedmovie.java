package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "likedmovie", indexes = {
        @Index(name = "user_id_idx", columnList = "user_id")
})
public class Likedmovie {
    @EmbeddedId
    private LikedmovieId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private es.uma.taw.arkhammovies.entity.Movie movie;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private es.uma.taw.arkhammovies.entity.User user;

}