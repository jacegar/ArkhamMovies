package es.uma.taw.arkhammovies.service;

import es.uma.taw.arkhammovies.dao.MovieRepository;
import es.uma.taw.arkhammovies.dao.UserRepository;
import es.uma.taw.arkhammovies.dto.DTO;
import es.uma.taw.arkhammovies.dto.MovieDTO;
import es.uma.taw.arkhammovies.dto.UserDTO;
import es.uma.taw.arkhammovies.entity.Movie;
import es.uma.taw.arkhammovies.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService extends DTOService<MovieDTO, Movie> {
    @Autowired protected MovieRepository movieRepository;
    @Autowired protected UserRepository userRepository;

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

    public List<MovieDTO> findMoviesById(List<Integer> movieIds) {
        List<Movie> movies = movieRepository.findAllById(movieIds);

        return this.entity2DTO(movies);
    }

    public UserDTO flipLike(Integer movieId, Integer userId) {
        User user = userRepository.findById(userId).get();
        Movie movie = movieRepository.findById(movieId).get();

        if(user.getMoviesLiked() == null) {
            user.setMoviesLiked(new ArrayList<Movie>());
            userRepository.save(user);
        }

        List<Movie> moviesLiked = user.getMoviesLiked();
        if(moviesLiked.contains(movie)) {
            moviesLiked.remove(movie);
            user.setMoviesLiked(moviesLiked);
        }else{
            moviesLiked.add(movie);
            user.setMoviesLiked(moviesLiked);
        }

        userRepository.save(user);
        return user.toDTO();
    }

    public UserDTO flipSave(Integer movieId, Integer userId) {
        User user = userRepository.findById(userId).get();
        Movie movie = movieRepository.findById(movieId).get();

        if(user.getMoviesSaved() == null) {
            user.setMoviesSaved(new ArrayList<Movie>());
            userRepository.save(user);
        }

        List<Movie> moviesSaved = user.getMoviesSaved();
        if(moviesSaved.contains(movie)) {
            moviesSaved.remove(movie);
            user.setMoviesSaved(moviesSaved);
        }else{
            moviesSaved.add(movie);
            user.setMoviesSaved(moviesSaved);
        }

        userRepository.save(user);
        return user.toDTO();
    }
}
