package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MovieCharacterRepository;
import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.PersonRepository;
import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.MovieCharacter;
import es.uma.taw.arkhammovies.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCharacterService extends DTOService<MovieCharacterDTO, MovieCharacter>{

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;
    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private PersonRepository personRepository;

    public List<MovieCharacterDTO> getCharactersByName(String name) {
        List<MovieCharacter> characters = movieCharacterRepository.getCharactersByName(name);

        return this.entity2DTO(characters);
    }

    public MovieCharacterDTO findCharacter(Integer id) {
        MovieCharacter character = movieCharacterRepository.findById(id).get();

        return this.toDTO(character);
    }

    public void saveCharacter(MovieCharacterDTO movieCharacterDTO) {
        MovieCharacter character = new MovieCharacter();

        character.setId(movieCharacterDTO.getId());
        character.setName(movieCharacterDTO.getName());
        character.setSurname1(movieCharacterDTO.getSurname1());
        character.setSurname2(movieCharacterDTO.getSurname2());
        character.setPhotoUrl(movieCharacterDTO.getPhotoUrl());
        character.setMovie(movieRepository.findById(movieCharacterDTO.getMovie()).get());
        character.setPerson(personRepository.findById(movieCharacterDTO.getPerson()).get());

        this.movieCharacterRepository.save(character);
    }

    public void deleteCharacterById(Integer id) {
        this.movieCharacterRepository.deleteById(id);
    }

    public List<MovieCharacterDTO> getCharactersFromMovie(Integer movieId) {
        Movie movie = movieRepository.findById(movieId).get();

        return this.entity2DTO(movie.getMovieCharacters());
    }

    public List<MovieCharacterDTO> getCharactersFromPerson(Integer personId) {
        Person person = personRepository.findById(personId).get();

        return this.entity2DTO(person.getMovieCharacters());
    }
}
