package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.MoviecrewRepository;
import es.uma.taw.arkhammovies.dao.PersonRepository;
import es.uma.taw.arkhammovies.dto.MoviecrewDTO;
import es.uma.taw.arkhammovies.entity.Moviecrew;
import es.uma.taw.arkhammovies.entity.MoviecrewId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//Autor: Juan Acevedo García 100%

@Service
public class MoviecrewService extends DTOService<MoviecrewDTO, Moviecrew> {
    @Autowired protected MoviecrewRepository moviecrewRepository;
    @Autowired protected MovieRepository movieRepository;
    @Autowired protected PersonRepository personRepository;

    public List<MoviecrewDTO> getMoviecrewsByPerson(Integer personId) {
        List<Moviecrew> jobs = moviecrewRepository.findCrewMembersByPerson(personId);

        return entity2DTO(jobs);
    }

    public List<MoviecrewDTO> getMoviecrewByMovie(Integer movieId) {
        List<Moviecrew> moviecrew = moviecrewRepository.findMoviecrewByMovie(movieId);

        return entity2DTO(moviecrew);
    }

    public void saveMoviecrew(MoviecrewDTO moviecrewDTO) {
        MoviecrewId moviecrewId = new MoviecrewId();
        moviecrewId.setPersonId(moviecrewDTO.getPersonId());
        moviecrewId.setMovieId(moviecrewDTO.getMovieId());

        Moviecrew moviecrew = moviecrewRepository.findById(moviecrewId).orElse(new Moviecrew());

        moviecrew.setId(moviecrewId);
        moviecrew.setMovie(movieRepository.findById(moviecrewId.getMovieId()).get());
        moviecrew.setPerson(personRepository.findById(moviecrewId.getPersonId()).get());
        moviecrew.setJob(moviecrewDTO.getJob());

        moviecrewRepository.save(moviecrew);
    }

    public void deleteMovieCrewById(Integer movieId, Integer personId) {
        MoviecrewId moviecrewId = new MoviecrewId();
        moviecrewId.setPersonId(personId);
        moviecrewId.setMovieId(movieId);

        moviecrewRepository.deleteById(moviecrewId);
    }

    public MoviecrewDTO getMovieCrewById(Integer movieId, Integer personId) {
        MoviecrewId moviecrewId = new MoviecrewId();
        moviecrewId.setPersonId(personId);
        moviecrewId.setMovieId(movieId);

        Moviecrew moviecrew = moviecrewRepository.findById(moviecrewId).get();

        return moviecrew.toDTO();
    }
}
