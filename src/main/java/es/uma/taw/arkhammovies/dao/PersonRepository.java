package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Autor: Juan Acevedo García (75%)

public interface PersonRepository extends JpaRepository<Person, Integer> {

    //Devuelve las personas que contengan el parámetro name en su nombre
    @Query("select p from Person p where p.name ilike %:name%")
    public List<Person> getPeopleByName(@Param("name") String name);

    //Devuelve los actores que contengan el parámetro name en su nombre
    @Query("select p from Person p where (p.movieCharacters is not empty or p.movieCharacters is empty and p.movieCrew is empty) and p.name ilike %:name%")
    public List<Person> getActorsByName(@Param("name") String name);

    //Devuelve las personas que hayan trabajado alguna vez como crewmember y que contengan el parámetro name en su nombre
    @Query("select p from Person p where p.movieCrew is not empty and p.name ilike %:name%")
    public List<Person> getCrewMembersByName(@Param("name") String name);

    //Devuelve las personas que han trabajado en una pelicula como crewmember
    @Query("select p from Person p join p.movieCrew mc where mc.movie.id = :movieId")
    List<Person> findPeopleByWorkedMovie(@Param("movieId") Integer movieId);
}

