package es.uma.taw.arkhammovies.dto;

import es.uma.taw.arkhammovies.entity.ReviewId;
import lombok.Data;

import java.util.Date;
import java.util.List;

//Autor: Juan Acevedo García 100%

@Data
public class MovieDTO {
    private Integer id;
    private String title;
    private Integer popularity;
    private Integer runtime;
    private Integer budget;
    private Integer revenue;
    private String overview;
    private Date releaseDate;
    private String status;
    private String homepage;
    private String tagline;
    private String photoUrl;
    private List<Integer> movieCharacters;
    private List<Integer> usersLiked;
    private List<Integer> productionCompanies;
    private List<Integer> productionCountries;
    private List<Integer> movieCrews;
    private List<Integer> genres;
    private List<Integer> keywords;
    private List<Integer> languages;
    private List<ReviewId> reviews;
    private List<Integer> usersSaved;
}
