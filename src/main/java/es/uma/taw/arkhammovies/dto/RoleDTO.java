package es.uma.taw.arkhammovies.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoleDTO {
    private Integer id;
    private String name;
    private Integer permission;
    private List<Integer> users;
}
