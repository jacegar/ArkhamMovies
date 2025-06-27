package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

@Data
public class KeywordDTO {
    private Integer id;
    private String name;
    private List<Integer> movies;
}
