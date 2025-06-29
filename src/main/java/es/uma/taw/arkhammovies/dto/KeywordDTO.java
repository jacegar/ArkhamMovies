package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

// ================================================================================
// Enrique Ibáñez: 100%
// ================================================================================

@Data
public class KeywordDTO {
    private Integer id;
    private String name;
    private List<Integer> movies;
}
