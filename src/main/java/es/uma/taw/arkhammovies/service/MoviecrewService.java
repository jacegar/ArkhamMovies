package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MoviecrewRepository;
import es.uma.taw.arkhammovies.dto.MoviecrewDTO;
import es.uma.taw.arkhammovies.entity.Moviecrew;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Autor: Juan Acevedo García 100%

@Service
public class MoviecrewService extends DTOService<MoviecrewDTO, Moviecrew> {
    @Autowired
    protected MoviecrewRepository crewmemberRepository;

    public List<MoviecrewDTO> getMoviecrewsByPerson(Integer personId) {
        List<Moviecrew> jobs = crewmemberRepository.findCrewMembersByPerson(personId);

        return entity2DTO(jobs);
    }

    public List<MoviecrewDTO> getMoviecrewByMovie(Integer movieId) {
        List<Moviecrew> moviecrew = crewmemberRepository.findMoviecrewByMovie(movieId);

        return entity2DTO(moviecrew);
    }
}
