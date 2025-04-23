package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "`moviecharacter`", indexes = {
        @Index(name = "movie_id_idx", columnList = "movie_id"),
        @Index(name = "person_id_idx", columnList = "person_id")
})
public class MovieCharacter {
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

    @Column(name = "photoUrl", length = 256)
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "movie_id", nullable = false)
    private es.uma.taw.arkhammovies.entity.Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "person_id")
    private es.uma.taw.arkhammovies.entity.Person person;

}