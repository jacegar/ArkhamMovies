package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "movie")
public class Movie {
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
    private Instant releaseDate;

    @Column(name = "status", length = 20)
    private String status;

    @Column(name = "homepage", length = 128)
    private String homepage;

    @Column(name = "tagline", length = 128)
    private String tagline;

    @Column(name = "photoUrl", length = 256)
    private String photoUrl;

    @OneToMany(mappedBy = "movie")
    private List<Character> characters = new ArrayList<>();

    @ManyToMany
    private List<es.uma.taw.arkhammovies.entity.User> usersLiked = new ArrayList<>();

    @ManyToMany
    private List<Productioncompany> productioncompanies = new ArrayList<>();

    @ManyToMany
    private List<es.uma.taw.arkhammovies.entity.Productioncountry> productioncountries = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<es.uma.taw.arkhammovies.entity.Moviecrew> moviecrews = new ArrayList<>();

    @ManyToMany
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany
    private List<Keyword> keywords = new ArrayList<>();

    @ManyToMany
    private List<Language> languages = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<es.uma.taw.arkhammovies.entity.Review> reviews = new ArrayList<>();

    @ManyToMany
    private List<es.uma.taw.arkhammovies.entity.User> usersSaved = new ArrayList<>();

}