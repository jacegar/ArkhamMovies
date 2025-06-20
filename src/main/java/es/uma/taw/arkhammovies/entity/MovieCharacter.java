package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "`moviecharacter`", indexes = {
        @Index(name = "movie_id_idx", columnList = "movie_id"),
        @Index(name = "person_id_idx", columnList = "person_id")
})
public class MovieCharacter implements DTO<MovieCharacterDTO>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "character_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname1", length = 30)
    private String surname1;

    @Column(name = "surname2", length = 30)
    private String surname2;

    @Column(name = "photo_url", length = 256)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private es.uma.taw.arkhammovies.entity.Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private es.uma.taw.arkhammovies.entity.Person person;

    @Override
    public MovieCharacterDTO toDTO() {
        MovieCharacterDTO characterDTO = new MovieCharacterDTO();
        characterDTO.setId(this.id);
        characterDTO.setName(this.name);
        characterDTO.setSurname1(this.surname1);
        characterDTO.setSurname2(this.surname2);
        characterDTO.setPhotoUrl(this.photoUrl);
        characterDTO.setMovie(this.movie.getId());
        characterDTO.setPerson(this.person.getId());

        return characterDTO;
    }
}