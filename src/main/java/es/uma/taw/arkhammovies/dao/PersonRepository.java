package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Autor: Juan Acevedo García (75%)

public interface PersonRepository extends JpaRepository<Person, Integer> {

    //Devuelve todos los actores
    @Query("select p from Person p where (p.movieCharacters is not empty or p.movieCharacters is empty and p.movieCrew is empty)")
    List<Person> getAllActors();

    //Devuelve las personas que hayan trabajado alguna vez como crewmember
    @Query("select p from Person p where p.movieCrew is not empty")
    public List<Person> getAllCrewMembers();

    //Devuelve las personas que han trabajado en una pelicula como crewmember
    @Query("select p from Person p join p.movieCrew mc where mc.movie.id = :movieId")
    List<Person> findPeopleByWorkedMovie(@Param("movieId") Integer movieId);

    //Devuelve los actores que contengan el parámetro name en su nombre o apellidos
    @Query("select p from Person p where (p.movieCharacters is not empty or p.movieCharacters is empty and p.movieCrew is empty)" +
            " and (concat(p.name, ' ', p.surname1, ' ', coalesce(p.surname2, '')) ilike %:name%)")
    List<Person> getActorsByNameOrSurname(@Param("name") String name);

    //Devuelve las personas que contengan el parámetro name en su nombre o apellidos
    @Query("select p from Person p where concat(p.name, ' ', p.surname1, ' ', coalesce(p.surname2, '')) ilike %:name%")
    List<Person> getPeopleByNameOrSurname(@Param("name") String name);

    //Devuelve las personas que hayan trabajado alguna vez como crewmember y que contengan el parámetro name en su nombre o apellidos
    @Query("select p from Person p where p.movieCrew is not empty and " +
            "(concat(p.name, ' ', p.surname1, ' ', coalesce(p.surname2, '')) ilike %:name%)")
    List<Person> getCrewMembersByNameOrSurname(@Param("name") String name);

    //Devuelve el numero de actores
    @Query("select count(p) from Person p where (p.movieCharacters is not empty or p.movieCharacters is empty and p.movieCrew is empty)")
    Integer getActorsCount();
}
