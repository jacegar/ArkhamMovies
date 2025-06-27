package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.KeywordDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "keyword", uniqueConstraints = {
        @UniqueConstraint(name = "name_UNIQUE", columnNames = {"name"})
})
public class Keyword implements Serializable, DTO<KeywordDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @ManyToMany
    @JoinTable(name = "moviekeyword",
            joinColumns = @JoinColumn(name = "keyword_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id"))
    private List<Movie> movies = new ArrayList<>();

    @Override
    public KeywordDTO toDTO() {
        KeywordDTO keywordDTO = new KeywordDTO();

        keywordDTO.setId(this.id);
        keywordDTO.setName(this.name);

        List<Integer> movieIds = new ArrayList<>();
        this.movies.forEach((final Movie movie) -> movieIds.add(movie.getId()));
        keywordDTO.setMovies(movieIds);

        return keywordDTO;
    }
}