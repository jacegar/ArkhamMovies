package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.PersonRepository;
import es.uma.taw.arkhammovies.dto.PersonDTO;
import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService extends DTOService<PersonDTO, Person>{

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> getPeopleByName(String name) {
        List<Person> people = personRepository.getPeopleByName(name);

        return this.entity2DTO(people);
    }

    public void savePerson(PersonDTO personDTO) {
        Person person = new Person();

        person.setId(personDTO.getId());
        person.setName(personDTO.getName());
        person.setSurname1(personDTO.getSurname1());
        person.setSurname2(personDTO.getSurname2());
        person.setGender(personDTO.getGender());
        person.setAge(personDTO.getAge());
        person.setPhotoUrl(personDTO.getPhotoUrl());
        person.setMovieCharacters(new ArrayList<>()); // Empieza sin personajes asignados

        this.personRepository.save(person);
    }

    public PersonDTO findPerson(Integer id) {
        Person person = personRepository.findById(id).get();

        return this.toDTO(person);
    }

    public void deletePersonById(Integer id) { this.personRepository.deleteById(id); }
}
