package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.PersonRepository;
import es.uma.taw.arkhammovies.dto.PersonDTO;
import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService extends DTOService<PersonDTO, Person>{

    @Autowired
    private PersonRepository personRepository;

    public List<PersonDTO> getPeopleByName(String name) {
        List<Person> people = personRepository.getPeopleByName(name);

        return this.entity2DTO(people);
    }

    public PersonDTO findPerson(Integer id) {
        Person person = personRepository.findById(id).get();

        return this.toDTO(person);
    }
}
