package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
    private Integer role;
    private List<Integer> moviesLiked;
    private List<Integer> reviews;
    private List<Integer> moviesSaved;
}
