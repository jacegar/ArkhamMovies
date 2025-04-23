package es.uma.taw.arkhammovies.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id", nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "surname1", nullable = false, length = 30)
    private String surname1;

    @Column(name = "surname2", length = 30)
    private String surname2;

    @Column(name = "gender", nullable = false)
    private Character gender;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "photoUrl", length = 256)
    private String photoUrl;

    @OneToMany(mappedBy = "person")
    private List<MovieCharacter> movieCharacters = new ArrayList<>();

}