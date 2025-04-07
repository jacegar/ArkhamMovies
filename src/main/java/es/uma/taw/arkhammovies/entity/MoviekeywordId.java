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
public class MoviekeywordId implements java.io.Serializable {
    private static final long serialVersionUID = -8800542700642856212L;
    @Column(name = "keyword_id", nullable = false)
    private Integer keywordId;

    @Column(name = "movie_id", nullable = false)
    private Integer movieId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        MoviekeywordId entity = (MoviekeywordId) o;
        return Objects.equals(this.keywordId, entity.keywordId) &&
                Objects.equals(this.movieId, entity.movieId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keywordId, movieId);
    }

}