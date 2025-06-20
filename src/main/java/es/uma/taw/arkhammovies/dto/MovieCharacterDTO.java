package es.uma.taw.arkhammovies.dto;

import lombok.Data;

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