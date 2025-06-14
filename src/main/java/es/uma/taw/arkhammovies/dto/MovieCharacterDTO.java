package es.uma.taw.arkhammovies.dto;

import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.Person;
import lombok.Data;

import java.util.List;

@Data
public class MovieCharacterDTO {
    private Integer id;
    private String name;
    private String surname1;
    private String surname2;
    private String photoUrl;
    private Integer movie;
    private Integer person;
}