package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

// ================================================================================
// Enrique Ibáñez: 100%
// ================================================================================

@Data
public class RoleDTO {
    private Integer id;
    private String name;
    private Integer permission;
    private List<Integer> users;
}
