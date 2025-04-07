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
public class MoviecountryId implements java.io.Serializable {
    private static final long serialVersionUID = 8173115985942612687L;
    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Column(name = "country_id", nullable = false)
    private Integer countryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MoviecountryId entity = (MoviecountryId) o;
        return Objects.equals(this.movieId, entity.movieId) &&
                Objects.equals(this.countryId, entity.countryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(movieId, countryId);
    }

}