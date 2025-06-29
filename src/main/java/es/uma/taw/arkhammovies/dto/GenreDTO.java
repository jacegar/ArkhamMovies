package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

@Data
public class GenreDTO {
    private Integer id;
    private String name;
    private List<Integer> movie_ids;
}
