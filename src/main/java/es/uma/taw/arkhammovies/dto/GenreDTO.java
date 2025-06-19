package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

//Autor: Juan Acevedo García 100%

@Data
public class GenreDTO {
    private Integer id;
    private String name;
    private List<Integer> movie_ids;
}
