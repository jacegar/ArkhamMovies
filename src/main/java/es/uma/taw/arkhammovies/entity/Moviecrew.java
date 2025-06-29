package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.MoviecrewDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "moviecrew", indexes = {
        @Index(name = "movie_id_idx", columnList = "movie_id"),
        @Index(name = "person_id_idx", columnList = "person_id")
})
public class Moviecrew implements DTO<MoviecrewDTO>, Serializable {
    @EmbeddedId
    private MoviecrewId id;

    @MapsId("movieId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @MapsId("personId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private es.uma.taw.arkhammovies.entity.Person person;

    @Column(name = "job", nullable = false, length = 45)
    private String job;

    @Override
    public MoviecrewDTO toDTO() {
        MoviecrewDTO crewDTO = new MoviecrewDTO();

        crewDTO.setJob(this.job);
        crewDTO.setPersonId(this.person.getId());
        crewDTO.setMovieId(this.movie.getId());

        return crewDTO;
    }
}