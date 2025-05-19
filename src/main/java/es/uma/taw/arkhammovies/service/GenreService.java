package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.GenreRepository;
import es.uma.taw.arkhammovies.dto.GenreDTO;
import es.uma.taw.arkhammovies.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GenreService extends DTOService<GenreDTO, Genre>{
    @Autowired
    protected GenreRepository genreRepository;

    public List<GenreDTO> getAllGenres(){
        List<GenreDTO> genres = new ArrayList<>();
        genreRepository.findAll().forEach(genre -> genres.add(genre.toDTO()));
        return genres;
    }
}
