package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService extends DTOService<MovieDTO, Movie> {
    @Autowired protected MovieRepository movieRepository;


    public List<MovieDTO> getMoviesSortedByPopularity(String title) {
        List<Movie> movies = movieRepository.getMoviesSortedByPopularity(title);

        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesSortedByReleaseDate(String title) {
        List<Movie> movies = movieRepository.getMoviesSortedByReleaseDate(title);

        return this.entity2DTO(movies);
    }

    public List<MovieDTO> getMoviesByTitle(String title) {
        List<Movie> movies = movieRepository.getMoviesByTitle(title);

        return this.entity2DTO(movies);
    }

    public MovieDTO findMovie(Integer id) {
        Movie movie = movieRepository.findById(id).get();

        return this.toDTO(movie);
    }
}
