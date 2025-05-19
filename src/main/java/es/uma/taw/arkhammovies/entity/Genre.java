package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.GenreDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "genre", uniqueConstraints = {
        @UniqueConstraint(name = "name_UNIQUE", columnNames = {"name"})
})
public class Genre implements DTO<GenreDTO>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @ManyToMany(mappedBy = "genres")
    private List<Movie> moviesgenre = new ArrayList<>();

    @Override
    public GenreDTO toDTO() {
        GenreDTO dto = new GenreDTO();

        dto.setId(id);
        dto.setName(name);

        List<Integer> moviesIds = new ArrayList<>();
        this.moviesgenre.forEach((final Movie movie) -> moviesIds.add(movie.getId()));
        dto.setMovie_ids(moviesIds);

        return dto;
    }
}