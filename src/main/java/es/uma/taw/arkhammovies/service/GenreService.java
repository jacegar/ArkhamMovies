package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.GenreRepository;
import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dto.GenreDTO;
import es.uma.taw.arkhammovies.entity.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

//Autor: Juan Acevedo García 100%

@Service
public class GenreService extends DTOService<GenreDTO, Genre>{
    @Autowired
    protected GenreRepository genreRepository;
    @Autowired
    protected MovieRepository movieRepository;

    public List<GenreDTO> getAllGenres(){
        List<Genre> genres = genreRepository.findAll();
        return entity2DTO(genres);
    }

    public List<GenreDTO> getGenresByMovie(Integer id) {
        List<Genre> genres = genreRepository.findGenresByMovie(id);

        return entity2DTO(genres);
    }

    public Map<String, Double> getGenresOrderedByFrequency() {
        List<Object[]> results = genreRepository.getGenresOrderedByFrequency();
        long moviesCount = movieRepository.count();
        Map<String, Double> frequencyMap = new LinkedHashMap<>();

        for (Object[] row : results) {
            String name = (String) row[0];
            Long genresCount = (Long) row[1];
            Double frequency = genresCount.doubleValue() / moviesCount;
            frequencyMap.put(name, frequency);
        }

        return frequencyMap;
    }

}
