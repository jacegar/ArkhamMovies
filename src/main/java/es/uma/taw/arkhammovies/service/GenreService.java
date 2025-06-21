package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.GenreRepository;
import es.uma.taw.arkhammovies.dto.GenreDTO;
import es.uma.taw.arkhammovies.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Autor: Juan Acevedo García 100%

@Service
public class GenreService extends DTOService<GenreDTO, Genre>{
    @Autowired
    protected GenreRepository genreRepository;

    public List<GenreDTO> getAllGenres(){
        List<Genre> genres = genreRepository.findAll();
        return entity2DTO(genres);
    }

    public List<GenreDTO> getGenresByMovie(Integer id) {
        List<Genre> genres = genreRepository.findGenresByMovie(id);

        return entity2DTO(genres);
    }
}
