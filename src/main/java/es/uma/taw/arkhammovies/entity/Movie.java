package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie implements DTO<MovieDTO>, Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "movie_id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 500)
    private String title;

    @Column(name = "popularity")
    private Integer popularity;

    @Column(name = "runtime")
    private Integer runtime;

    @Column(name = "budget")
    private Integer budget;

    @Column(name = "revenue")
    private Integer revenue;

    @Column(name = "overview", length = 300)
    private String overview;

    @Column(name = "release_date")
    private Date releaseDate;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "homepage", length = 128)
    private String homepage;

    @Column(name = "tagline", length = 128)
    private String tagline;

    @Column(name = "photo_url", length = 256)
    private String photoUrl;

    @OneToMany(mappedBy = "movie")
    private List<MovieCharacter> movieCharacters = new ArrayList<>();

    @ManyToMany(mappedBy = "moviesLiked")
    private List<es.uma.taw.arkhammovies.entity.User> usersLiked = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "moviecompany",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id"))
    private List<Productioncompany> productioncompanies = new ArrayList<>();

    @ManyToMany
    @JoinTable(name = "moviecountry",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "country_id"))
    private List<es.uma.taw.arkhammovies.entity.Productioncountry> productioncountries = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<es.uma.taw.arkhammovies.entity.Moviecrew> moviecrews = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Keyword> keywords = new ArrayList<>();

    @ManyToMany(mappedBy = "movies")
    private List<Language> languages = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<es.uma.taw.arkhammovies.entity.Review> reviews = new ArrayList<>();

    @ManyToMany(mappedBy = "moviesSaved")
    private List<es.uma.taw.arkhammovies.entity.User> usersSaved = new ArrayList<>();

    @Override
    public MovieDTO toDTO() {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setId(this.id);
        movieDTO.setTitle(this.title);
        movieDTO.setPopularity(this.popularity);
        movieDTO.setRuntime(this.runtime);
        movieDTO.setBudget(this.budget);
        movieDTO.setRevenue(this.revenue);
        movieDTO.setOverview(this.overview);
        movieDTO.setReleaseDate(this.releaseDate);
        movieDTO.setStatus(this.status);
        movieDTO.setHomepage(this.homepage);
        movieDTO.setTagline(this.tagline);
        movieDTO.setPhotoUrl(this.photoUrl);

        List<Integer> genresIds = new ArrayList<>();
        this.genres.forEach((final Genre genre) -> genresIds.add(genre.getId()));
        movieDTO.setGenres(genresIds);

        List<Integer> keywordsIds = new ArrayList<>();
        this.keywords.forEach((final Keyword keyword) -> keywordsIds.add(keyword.getId()));
        movieDTO.setKeywords(keywordsIds);

        List<Integer> languagesIds = new ArrayList<>();
        this.languages.forEach((final Language language) -> languagesIds.add(language.getId()));
        movieDTO.setLanguages(languagesIds);

        //No se hasta que punto esto funciona
        List<ReviewId> reviewsIds = new ArrayList<>();
        this.reviews.forEach((final Review review) -> reviewsIds.add(review.getId()));
        movieDTO.setReviews(reviewsIds);

        List<Integer> usersSavedIds = new ArrayList<>();
        this.usersSaved.forEach((final User user) -> usersSavedIds.add(user.getId()));
        movieDTO.setUsersSaved(usersSavedIds);

        return movieDTO;
    }
}