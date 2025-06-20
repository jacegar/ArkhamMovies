package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

//Autor: Juan Acevedo García (60%)

public interface PersonRepository extends JpaRepository<Person, Integer> {

    //Devuelve las personas que contengan el parámetro name en su nombre
    @Query("select p from Person p where p.name ilike %:name%")
    public List<Person> getPeopleByName(String name);

    //Devuelve los actores que contengan el parámetro name en su nombre
    @Query("select p from Person p where p.movieCharacters is not empty and p.name ilike %:name%")
    public List<Person> getActorsByName(String name);

    //Devuelve los crewMembers que contengan el parámetro name en su nombre
    @Query("select p from Person p where p.movieCrew is not empty and p.name ilike %:name%")
    public List<Person> getCrewMembersByName(String name);
}

