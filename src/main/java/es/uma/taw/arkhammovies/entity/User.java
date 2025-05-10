package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "user", indexes = {
        @Index(name = "role_id_idx", columnList = "role_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "email_UNIQUE", columnNames = {"email"})
})
public class User implements Serializable, DTO<UserDTO> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @Column(name = "nickname", nullable = false, length = 25)
    private String nickname;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 25)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToMany
    private List<Movie> moviesLiked = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();

    @ManyToMany
    private List<Movie> moviesSaved = new ArrayList<>();

    @Override
    public UserDTO toDTO() {
        UserDTO userDTO = new UserDTO();

        userDTO.setId(this.id);
        userDTO.setNickname(this.nickname);
        userDTO.setEmail(this.email);
        userDTO.setPassword(this.password);
        userDTO.setRole(this.role.getId());

        /* Da error al iniciar sesión, lo dejo comentado para seguir
        List<Integer> moviesLikedIds = new ArrayList<>();
        this.moviesLiked.forEach((final Movie movie) -> moviesLikedIds.add(movie.getId()));
        userDTO.setMoviesLiked(moviesLikedIds);

        List<ReviewId> reviewsIds = new ArrayList<>();
        this.reviews.forEach((final Review review) -> reviewsIds.add(review.getId()));
        userDTO.setReviews(reviewsIds);

        List<Integer> moviesSavedIds = new ArrayList<>();
        this.moviesSaved.forEach((final Movie movie) -> moviesSavedIds.add(movie.getId()));
        userDTO.setMoviesSaved(moviesSavedIds);
         */

        return userDTO;
    }
}