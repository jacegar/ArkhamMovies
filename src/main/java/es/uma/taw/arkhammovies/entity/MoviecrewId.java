package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Embeddable
public class MoviecrewId implements java.io.Serializable {
    private static final long serialVersionUID = -7975635241790524763L;
    @Column(name = "person_id", nullable = false)
    private Integer personId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MoviecrewId entity = (MoviecrewId) o;
        return Objects.equals(this.personId, entity.personId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, movieId);
    }

}