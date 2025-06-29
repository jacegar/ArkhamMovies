package es.uma.taw.arkhammovies.dto;

import es.uma.taw.arkhammovies.entity.ReviewId;
import lombok.Data;

import java.util.List;

// ================================================================================
// Juan Acevedo: 100%
// ================================================================================

@Data
public class UserDTO {
    private Integer id;
    private String nickname;
    private String email;
    private String password;
    private Integer role;
    private List<Integer> moviesLiked;
    private List<ReviewId> reviews;
    private List<Integer> moviesSaved;
}
