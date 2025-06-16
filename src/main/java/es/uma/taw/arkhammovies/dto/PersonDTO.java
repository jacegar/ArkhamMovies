package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

@Data
public class PersonDTO {
    private Integer id;
    private String name;
    private String surname1;
    private String surname2;
    private Character gender;
    private Integer age;
    private String photoUrl;
    private List<Integer> movieCharacters;
}
