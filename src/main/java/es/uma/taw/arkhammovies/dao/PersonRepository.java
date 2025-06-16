package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    //Devuelve las personas que contengan el parámetro name en su nombre
    @Query("select p from Person p where p.name ilike %:name%")
    public List<Person> getPeopleByName(String name);
}

