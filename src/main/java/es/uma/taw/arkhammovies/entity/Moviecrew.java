package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "moviecrew", indexes = {
        @Index(name = "movie_id_idx", columnList = "movie_id")
})
public class Moviecrew {
    @EmbeddedId
    private MoviecrewId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @Column(name = "job", nullable = false, length = 45)
    private String job;

}