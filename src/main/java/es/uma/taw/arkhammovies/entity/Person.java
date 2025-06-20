package es.uma.taw.arkhammovies.entity;

import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.PersonDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "person")
public class Person implements DTO<PersonDTO>, Serializable {
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

    @Column(name = "photo_url", length = 256)
    private String photoUrl;

    @OneToMany(mappedBy = "person")
    private List<MovieCharacter> movieCharacters = new ArrayList<>();


    @Override
    public PersonDTO toDTO() {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setId(this.id);
        personDTO.setName(this.name);
        personDTO.setSurname1(this.surname1);
        personDTO.setSurname2(this.surname2);
        personDTO.setGender(this.gender);
        personDTO.setAge(this.age);
        personDTO.setPhotoUrl(this.photoUrl);

        List<Integer> movieCharacters = new ArrayList<>();
        this.movieCharacters.forEach((final MovieCharacter movieCharacter) -> movieCharacters.add(movieCharacter.getId()));
        personDTO.setMovieCharacters(movieCharacters);

        return personDTO;
    }
}