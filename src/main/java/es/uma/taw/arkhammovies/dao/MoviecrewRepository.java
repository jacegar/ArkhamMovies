package es.uma.taw.arkhammovies.dao;

import es.uma.taw.arkhammovies.entity.Moviecrew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//Autor: Juan Acevedo García (100%)

public interface MoviecrewRepository extends JpaRepository<Moviecrew, Integer> {
    //Devuelve los trabajos en produccion de una persona
    @Query("select m from Moviecrew m where m.person.id = :personId")
    public List<Moviecrew> getCrewMembersByPerson(@Param("personId") Integer personId);
}
