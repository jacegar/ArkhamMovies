package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MovieCharacterRepository;
import es.uma.taw.arkhammovies.dto.MovieCharacterDTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.MovieCharacter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieCharacterService extends DTOService<MovieCharacterDTO, MovieCharacter>{

    @Autowired
    private MovieCharacterRepository movieCharacterRepository;

    public List<MovieCharacterDTO> getAllCharacters() {
        List<MovieCharacter> characters = movieCharacterRepository.findAll();

        return this.entity2DTO(characters);
    }

    public MovieCharacterDTO findCharacter(Integer id) {
        MovieCharacter character = movieCharacterRepository.findById(id).get();

        return this.toDTO(character);
    }
}
